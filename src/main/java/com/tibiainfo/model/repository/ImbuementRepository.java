package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.imbuement.Imbuement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImbuementRepository extends JpaRepository<Imbuement, Long>, JpaSpecificationExecutor<Imbuement> {

    @Query(value = "select image from imbuement where article_id = :id", nativeQuery = true)
    String getImageById(@Param("id") Long id);
}
