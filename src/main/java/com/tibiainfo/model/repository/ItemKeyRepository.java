package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.item.key.ItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ItemKeyRepository extends JpaRepository<ItemKey, Long>, JpaSpecificationExecutor<ItemKey> {
}
