package com.vmware.talentboost.ics;

import antlr.collections.List;
import com.vmware.talentboost.ics.data.Image;
import com.vmware.talentboost.ics.data.ServiceType;
import com.vmware.talentboost.ics.repository.JpaImageRepository;
import com.vmware.talentboost.ics.service.impl.ImageServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ImageServiceTests {

    @Mock
    JpaImageRepository imageRepository;
    ImageServiceImpl imageService;

    @BeforeEach
    void setup() {
        this.imageService = new ImageServiceImpl(imageRepository);
    }


    @Test
    void getAllImagesTest() {
        Mockito.when(imageRepository.findAll()).thenReturn(new ArrayList<Image>());

        assertTrue(imageService.getAllImages() instanceof ArrayList<?>);
    }

    @Test
    void getImageByIdTest() {
        Mockito.when(imageRepository.getImageById(Mockito.anyInt())).thenReturn(Optional.of(new Image()));

        assertNotNull(imageService.getImageById(Mockito.anyInt()));
    }

    @Test
    void getImageByUrlTest(){
        Mockito.when(imageRepository.getImageByUrl(Mockito.anyString())).thenReturn(Optional.of(new Image()));

        assertNotNull(imageService.getImageByUrl(Mockito.anyString()));
    }

    @Test
    void addImageTest(){
        Image img = new Image();
        Mockito.when(imageRepository.saveAndFlush(img)).thenReturn(new Image());

        assertNotNull(imageService.addImage(img));
    }

    @Test
    void deleteImage(){
        imageService.deleteImage(Mockito.anyInt());
        verify(imageRepository, times(1)).deleteById(Mockito.anyInt());
    }
}
