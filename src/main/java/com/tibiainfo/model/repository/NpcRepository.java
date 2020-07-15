package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.npc.Npc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NpcRepository extends JpaRepository<Npc, Long>, JpaSpecificationExecutor<Npc> {

    @Query(value = "select image from npc where article_id = :id", nativeQuery = true)
    String getImageById(@Param("id") Long id);
}