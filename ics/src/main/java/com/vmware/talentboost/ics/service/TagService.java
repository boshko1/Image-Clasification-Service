package com.vmware.talentboost.ics.service;

import com.vmware.talentboost.ics.data.Image;
import com.vmware.talentboost.ics.data.Tag;
import com.vmware.talentboost.ics.data.TagImageLink;

import java.util.List;
import java.util.Optional;

public interface TagService {

    List<Tag> getAllTags();

    Tag getTag(int id);

    Optional<Tag> getTagByName(String tagName);

    Tag addTag(Tag tag);

    void deleteTag(int id);

    List<Image> getImagesByTag(String tagName);
}
