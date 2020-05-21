package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.Creature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatureRepository extends JpaRepository<Creature, Long>, JpaSpecificationExecutor<Creature> {

    @Query(value = "select image from creature where article_id = :id ", nativeQuery = true)
    String getImageById(@Param("id") Long id);
}
