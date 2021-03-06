package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {


    @Query(value = "select image from item where article_id = :id", nativeQuery = true)
    String getImageById(@Param("id") Long id);
}