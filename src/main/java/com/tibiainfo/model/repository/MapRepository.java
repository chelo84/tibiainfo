package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.map.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MapRepository extends JpaRepository<Map, Long>, JpaSpecificationExecutor<Map> {

    @Query(value = "select image from map where z = :z", nativeQuery = true)
    String getImageByZ(@Param("z") Long z);

}
