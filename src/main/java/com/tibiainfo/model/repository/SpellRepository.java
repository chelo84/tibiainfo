package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.spell.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellRepository extends JpaRepository<Spell, Long>, JpaSpecificationExecutor<Spell> {

    @Query(value = "select image from spell where article_id = :id", nativeQuery = true)
    String getImageById(@Param("id") Long id);
}
