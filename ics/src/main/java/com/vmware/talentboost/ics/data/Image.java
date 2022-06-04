package com.vmware.talentboost.ics.data;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "url", unique = true, nullable = false)
    private String url;

    @Column(name = "upload_date")
    private String upload_date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ServiceType service;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
    private List<TagImageLink> links;


    public Image(String url, String upload_date, ServiceType service, int width, int height) {
        this.url = url;
        this.upload_date = upload_date;
        this.service = service;
        this.width = width;
        this.height = height;
        this.links = new ArrayList<TagImageLink>();
    }

    public Image() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    public ServiceType getService() {
        return service;
    }

    public void setService(ServiceType service) {
        this.service = service;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<TagImageLink> getLinks() {
        return links;
    }

    public void setLinks(List<TagImageLink> links) {
        this.links = links;
    }

    public void addLink(TagImageLink tagImageLink){
        this.links.add(tagImageLink);
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", upload_date='" + upload_date + '\'' +
                ", service=" + service +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
