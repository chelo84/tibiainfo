package com.tibiainfo.model.repository;

import com.tibiainfo.model.Creature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatureRepository extends JpaRepository<Creature, Long>, JpaSpecificationExecutor<Creature> {
}
