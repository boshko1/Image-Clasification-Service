package com.vmware.talentboost.ics;

import com.vmware.talentboost.ics.data.Image;
import com.vmware.talentboost.ics.data.TagImageLink;
import com.vmware.talentboost.ics.data.TagImageLinkID;
import com.vmware.talentboost.ics.repository.JpaTagImageLinkRepository;
import com.vmware.talentboost.ics.service.impl.TagImageLinkServiceImpl;
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
public class TagImageLinkServiceTests {

    @Mock
    JpaTagImageLinkRepository tagImageLinkRepository;
    TagImageLinkServiceImpl tagImageLinkService;


    @BeforeEach
    void setup(){
        this.tagImageLinkService = new TagImageLinkServiceImpl(tagImageLinkRepository);
    }


    @Test
    void getAllTagImageLinksTest() {
        Mockito.when(tagImageLinkRepository.findAll()).thenReturn(new ArrayList<TagImageLink>());

        assertTrue(tagImageLinkService.getAllTagImageLinks() instanceof ArrayList<?>);
    }

    @Test
    void getTagImageLinkByIdTest() {
        TagImageLinkID tagImageLinkID = new TagImageLinkID();
        Mockito.when(tagImageLinkRepository.getById(tagImageLinkID)).thenReturn(new TagImageLink());

        assertNotNull(tagImageLinkService.getTagImageLink(tagImageLinkID));
    }

    @Test
    void addTagImageLinkTest(){
        TagImageLink tagImageLink = new TagImageLink();
        Mockito.when(tagImageLinkRepository.saveAndFlush(tagImageLink)).thenReturn(new TagImageLink());

        assertNotNull(tagImageLinkService.addTagImageLink(tagImageLink));
    }

    @Test
    void deleteTagImageLinkTest(){
        TagImageLinkID tagImageLinkID = new TagImageLinkID();

        tagImageLinkService.deleteTagImageLink(tagImageLinkID);
        verify(tagImageLinkRepository, times(1)).deleteById(tagImageLinkID);
    }
}
