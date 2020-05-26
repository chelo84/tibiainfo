package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.outfit.Outfit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OutfitRepository extends JpaRepository<Outfit, Long>, JpaSpecificationExecutor<Outfit> {

//    @Query(value = "select image from outfit where article_id = :id ", nativeQuery = true)
//    String getImageById(@Param("id") Long id);

}
