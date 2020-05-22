package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.creature.CreatureDrop;
import com.tibiainfo.model.entity.creature.CreatureDropId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreatureDropRepository extends JpaRepository<CreatureDrop, CreatureDropId> {

    List<CreatureDrop> findAllByCreatureId(Long id);

}
