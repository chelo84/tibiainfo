package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.rashid.Rashid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RashidRepository extends JpaRepository<Rashid, Long>, JpaSpecificationExecutor<Rashid> {
}
