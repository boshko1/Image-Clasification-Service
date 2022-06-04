package com.vmware.talentboost.ics.service;

import com.vmware.talentboost.ics.data.TagImageLink;
import com.vmware.talentboost.ics.data.TagImageLinkID;

import java.util.List;

public interface TagImageLinkService {

    List<TagImageLink> getAllTagImageLinks();

    TagImageLink getTagImageLink(TagImageLinkID tagImageLinkID);

    TagImageLink addTagImageLink(TagImageLink tagImageLink);

    void deleteTagImageLink(TagImageLinkID tagImageLinkID);
}
