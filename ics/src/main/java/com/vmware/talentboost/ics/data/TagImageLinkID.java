package com.vmware.talentboost.ics.data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class TagImageLinkID implements Serializable {

    @Column(name = "image_id")
    private int imageId;

    @Column(name = "tag_id")
    private int tagId;

    public TagImageLinkID(){};

    public TagImageLinkID(int imageId, int tagId) {
        this.imageId = imageId;
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagImageLinkID that = (TagImageLinkID) o;
        return imageId == that.imageId && tagId == that.tagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, tagId);
    }
}
