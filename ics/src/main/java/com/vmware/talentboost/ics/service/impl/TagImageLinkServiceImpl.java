package com.vmware.talentboost.ics.service.impl;

import com.vmware.talentboost.ics.data.TagImageLink;
import com.vmware.talentboost.ics.data.TagImageLinkID;
import com.vmware.talentboost.ics.repository.JpaTagImageLinkRepository;
import com.vmware.talentboost.ics.service.TagImageLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagImageLinkServiceImpl implements TagImageLinkService {

    private final JpaTagImageLinkRepository repository;

    @Autowired
    public TagImageLinkServiceImpl(JpaTagImageLinkRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TagImageLink> getAllTagImageLinks() {
        return repository.findAll();
    }

    @Override
    public TagImageLink getTagImageLink(TagImageLinkID tagImageLinkID) {
        return repository.getById(tagImageLinkID);
    }

    @Override
    public TagImageLink addTagImageLink(TagImageLink tagImageLink) {
        return repository.saveAndFlush(tagImageLink);
    }

    @Override
    public void deleteTagImageLink(TagImageLinkID tagImageLinkID) {
        repository.deleteById(tagImageLinkID);
    }
}