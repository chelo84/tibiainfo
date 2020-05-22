package com.tibiainfo.model.repository;

import com.tibiainfo.model.entity.creature.CreatureDrop;
import com.tibiainfo.model.entity.creature.CreatureDropId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreatureDropRepository extends JpaRepository<CreatureDrop, CreatureDropId> {

    List<CreatureDrop> findAllByCreatureId(Long id);

}
