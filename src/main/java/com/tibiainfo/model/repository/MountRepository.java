package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.mount.Mount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MountRepository extends JpaRepository<Mount, Long>, JpaSpecificationExecutor<Mount> {

    @Query(value = "select image from mount where article_id = :id", nativeQuery = true)
    String getImageById(@Param("id") Long id);
}
