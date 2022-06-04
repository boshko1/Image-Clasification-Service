package com.vmware.talentboost.ics.repository;

import com.vmware.talentboost.ics.data.Tag;
import com.vmware.talentboost.ics.data.TagImageLink;
import com.vmware.talentboost.ics.data.TagImageLinkID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaTagImageLinkRepository extends JpaRepository<TagImageLink, TagImageLinkID> {
    List<TagImageLink> getLinksByTag(Optional<Tag> tag);
}
