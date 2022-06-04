package com.vmware.talentboost.ics.controller;

import com.vmware.talentboost.ics.data.Image;
import com.vmware.talentboost.ics.data.ServiceType;
import com.vmware.talentboost.ics.data.Tag;
import com.vmware.talentboost.ics.data.TagImageLink;
import com.vmware.talentboost.ics.service.ImageService;
import com.vmware.talentboost.ics.service.TagImageLinkService;
import com.vmware.talentboost.ics.service.TagService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;


@RestController
@RequestMapping("/images")
public class ImageController {

    final int MIN_CONFIDENCE = 30;

    private final ImageService imageService;
    private final TagService tagService;
    private final TagImageLinkService tagImageLinkService;

    @Autowired
    public ImageController(ImageService imageService, TagService tagService, TagImageLinkService tagImageLinkService) {
        this.imageService = imageService;
        this.tagService = tagService;
        this.tagImageLinkService = tagImageLinkService;
    }

    @PostMapping
    public Image searchImage(@RequestBody String image_url) throws IOException {

        String credentialsToEncode = "acc_93eb0d77958d763" + ":" + "63fa935952dffe421891b900239c89ae";
        String basicAuth = Base64.getEncoder().encodeToString(credentialsToEncode.getBytes(StandardCharsets.UTF_8));

        String endpoint_url = "https://api.imagga.com/v2/tags";
        String url = endpoint_url + "?image_url=" + image_url;

        URL urlObject = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
        connection.setRequestProperty("Authorization", "Basic " + basicAuth);

        BufferedReader connectionInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String jsonResponse = connectionInput.readLine();
        connectionInput.close();


        URL url2 = new URL(image_url);
        java.awt.Image image2 = new ImageIcon(url2).getImage();
        int imgWidth = image2.getWidth(null);
        int imgHeight = image2.getHeight(null);

        Image image = new Image(image_url, String.valueOf(java.time.LocalDate.now()), ServiceType.Imagga, imgWidth, imgHeight);

        try {
            imageService.addImage(image);
        } catch (Exception e) {
            return null;
        }


        JSONObject obj = new JSONObject(jsonResponse);
        JSONObject result = obj.getJSONObject("result");
        JSONArray arr = result.getJSONArray("tags");

        ArrayList<TagImageLink> links = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {

            JSONObject conf = arr.getJSONObject(i);
            double confidence = conf.getDouble("confidence");

            if (confidence < MIN_CONFIDENCE) {
                break;
            }

            JSONObject tag = conf.getJSONObject("tag");
            String name = tag.getString("en");

            if (this.tagService.getTagByName(name).isPresent()) {
                Tag tag2 = this.tagService.getTagByName(name).get();
                links.add(tagImageLinkService.addTagImageLink(new TagImageLink(image.getId(), tag2.getId(), tag2.getName(), image, tag2, confidence)));
            } else {
                Tag tag2 = tagService.addTag(new Tag(name));

                links.add(tagImageLinkService.addTagImageLink(new TagImageLink(image.getId(), tag2.getId(), tag2.getName(), image, tag2, confidence)));            }
        }

        Image image3 = this.imageService.getImageByUrl(image_url);

        for(TagImageLink link : links){
            image3.getLinks().add(link);
        }

        return image3;
    }

    @GetMapping("{id}")
    public Image searchImageById(@PathVariable final Integer id) {
        return imageService.getImageById(id);
    }

    @GetMapping("tag/{tagName}")
    public List<Image> getImagesByTag(@PathVariable final String tagName) {
        return tagService.getImagesByTag(tagName);
    }

    @GetMapping("url")
    public Image searchImageByUrl(@RequestBody String url) {
        return imageService.getImageByUrl(url);
    }

    @GetMapping
    public List<Image> getAllImages() {
        return imageService.getAllImages();
    }
}
