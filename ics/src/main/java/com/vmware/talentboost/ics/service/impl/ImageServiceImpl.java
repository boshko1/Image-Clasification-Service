package com.vmware.talentboost.ics.service.impl;

import com.vmware.talentboost.ics.data.Image;
import com.vmware.talentboost.ics.repository.JpaImageRepository;
import com.vmware.talentboost.ics.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final JpaImageRepository repository;

    @Autowired
    public ImageServiceImpl(JpaImageRepository jpaImageRepository){
        this.repository = jpaImageRepository;
    }

    @Override
    public List<Image> getAllImages() {
        return repository.findAll();
    }

    @Override
    public  Image getImageById(int id) {
        Optional<Image> image = repository.getImageById(id);

        if(image.isPresent()){
            return image.get();
        }
        else{
            throw new NoSuchElementException("Image not found!");
        }
    }

    @Override
    public Image getImageByUrl(String url) {
        Optional<Image> image = repository.getImageByUrl(url);

        if(image.isPresent()){
            return image.get();
        }
        else{
            throw new NoSuchElementException("Image not found!");
        }
    }

    @Override
    public Image addImage(Image image) {
        return repository.saveAndFlush(image);
    }

    @Override
    public void deleteImage(int id) {
        repository.deleteById(id);
    }
}
