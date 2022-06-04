package com.vmware.talentboost.ics.service.impl;

import com.vmware.talentboost.ics.data.Image;
import com.vmware.talentboost.ics.data.Tag;
import com.vmware.talentboost.ics.data.TagImageLink;
import com.vmware.talentboost.ics.repository.JpaImageRepository;
import com.vmware.talentboost.ics.repository.JpaTagImageLinkRepository;
import com.vmware.talentboost.ics.repository.JpaTagRepository;
import com.vmware.talentboost.ics.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final JpaTagRepository tagRepository;
    private final JpaTagImageLinkRepository tagImageLinkRepository;
    private final JpaImageRepository imageRepository;

    @Autowired
    public TagServiceImpl(JpaTagRepository repository, JpaTagImageLinkRepository repository2, JpaImageRepository repository3) {
        this.tagRepository = repository;
        this.tagImageLinkRepository = repository2;
        this.imageRepository = repository3;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTag(int id) {
        return tagRepository.getById(id);
    }

    @Override
    public Optional<Tag> getTagByName(String tagName) {
        return this.tagRepository.getTagByName(tagName);
    }

    @Override
    public Tag addTag(Tag tag) {
        return tagRepository.saveAndFlush(tag);
    }

    @Override
    public void deleteTag(int id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<Image> getImagesByTag(String tagName) {

        List<Image> result = new ArrayList<>();

        if (this.tagRepository.getTagByName(tagName).isPresent()) {

        List<TagImageLink> list = this.tagImageLinkRepository.getLinksByTag(this.tagRepository.getTagByName(tagName));


            for (TagImageLink link : list) {
                result.add(this.imageRepository.getById(link.getImageId()));
            }

        }
        else{
            return new ArrayList<>();
        }

        return result;
    }
}
