package com.vmware.talentboost.ics.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy ="tag", cascade = CascadeType.ALL)
    private List<TagImageLink> links;

    public Tag(){};

    public Tag(String name) {
        this.name = name;
        this.links = new ArrayList<TagImageLink>();
    }

    public List<TagImageLink> getLinks() {
        return links;
    }

    public void setLinks(List<TagImageLink> links) {
        this.links = links;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addLink(TagImageLink tagImageLink){
        this.links.add(tagImageLink);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
