package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.query.CreatureQueryDTO;
import com.tibiainfo.model.entity.creature.Creature;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CreatureSpecification extends TibiaInfoSpecification<Creature> {

    CreatureQueryDTO creatureQueryDto;

    public void instructions() {
        equalIgnoreCase("name", creatureQueryDto.getName());
    }

}
