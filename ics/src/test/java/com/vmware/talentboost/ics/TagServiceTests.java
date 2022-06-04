package com.vmware.talentboost.ics;


import com.vmware.talentboost.ics.data.Tag;
import com.vmware.talentboost.ics.repository.JpaImageRepository;
import com.vmware.talentboost.ics.repository.JpaTagImageLinkRepository;
import com.vmware.talentboost.ics.repository.JpaTagRepository;
import com.vmware.talentboost.ics.service.impl.TagServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class TagServiceTests {

    @Mock
    JpaTagRepository tagRepository;
    @Mock
    JpaImageRepository imageRepository;
    @Mock
    JpaTagImageLinkRepository tagImageLinkRepository;
    TagServiceImpl tagService;

    @BeforeEach
    void setup(){
        this.tagService = new TagServiceImpl(tagRepository, tagImageLinkRepository, imageRepository);
    }

    @Test
    void getAllTagsTest() {
        Mockito.when(tagRepository.findAll()).thenReturn(new ArrayList<Tag>());

        assertTrue(tagService.getAllTags() instanceof ArrayList<?>);
    }

    @Test
    void getTagByIdTest() {
        Mockito.when(tagRepository.getById(Mockito.anyInt())).thenReturn(new Tag());

        assertNotNull(tagService.getTag(Mockito.anyInt()));
    }

    @Test
    void getTagByNameTest() {
        Mockito.when(tagRepository.getTagByName(Mockito.anyString())).thenReturn(Optional.of(new Tag()));

        assertNotNull(tagService.getTagByName(Mockito.anyString()));
    }

    @Test
    void addImageTest(){
        Tag tag = new Tag();
        Mockito.when(tagRepository.saveAndFlush(tag)).thenReturn(new Tag());

        assertNotNull(tagService.addTag(tag));
    }

    @Test
    void deleteTag(){
        tagService.deleteTag(Mockito.anyInt());
        verify(tagRepository, times(1)).deleteById(Mockito.anyInt());
    }
}
