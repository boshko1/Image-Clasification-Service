package com.vmware.talentboost.ics.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tag_image_link")
@IdClass(TagImageLinkID.class)
public class TagImageLink {

    @Id
    @JsonIgnore
    private int imageId;

    @Id
    @JsonIgnore
    private int tagId;

    private String tag_name;

    @ManyToOne
    @MapsId("imageId")
    @JoinColumn(name = "image_id")
    @JsonIgnore
    private Image image;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    @JsonIgnore
    private Tag tag;

    @Column(name = "confidence")
    private Double confidence;

    public TagImageLink(){};

    public TagImageLink(int imageId, int tagId, String tag_name, Image image, Tag tag, Double confidence) {
        this.imageId = imageId;
        this.tagId = tagId;
        this.tag_name = tag_name;
        this.image = image;
        this.tag = tag;
        this.confidence = confidence;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagImageLink that = (TagImageLink) o;
        return imageId == that.imageId && tagId == that.tagId && Objects.equals(tag_name, that.tag_name) && Objects.equals(image, that.image) && Objects.equals(tag, that.tag) && Objects.equals(confidence, that.confidence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, tagId, tag_name, image, tag, confidence);
    }
}
