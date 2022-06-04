package com.vmware.talentboost.ics.repository;

import com.vmware.talentboost.ics.data.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaImageRepository extends JpaRepository<Image, Integer> {
     Optional<Image> getImageByUrl(String url);
     Optional<Image> getImageById(int id);
}
