package com.vmware.talentboost.ics.service;

import com.vmware.talentboost.ics.data.Image;
import java.util.List;
import java.util.Optional;

public interface ImageService {

    List<Image> getAllImages();

    Image getImageById(int id);

    Image getImageByUrl(String url);

    Image addImage(Image image);

    void deleteImage(int id);

}
