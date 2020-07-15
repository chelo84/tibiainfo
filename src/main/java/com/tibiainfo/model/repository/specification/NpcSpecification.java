package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.query.NpcQueryDTO;
import com.tibiainfo.model.entity.npc.Npc;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class NpcSpecification extends TibiaInfoSpecification<Npc>{

    NpcQueryDTO npcQueryDTO;

    @Override
    public void instructions() {
        equalIgnoreCase("city", npcQueryDTO.getCity())
                .equalIgnoreCase("name", npcQueryDTO.getName());
    }

}
