package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.query.SpellQueryDTO;
import com.tibiainfo.model.entity.spell.Spell;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SpellSpecification extends TibiaInfoSpecification<Spell> {

    SpellQueryDTO spellQueryDTO;

    @Override
    public void instructions() {
        equalIgnoreCase("name", spellQueryDTO.getName())
                .equalIgnoreCase("words", spellQueryDTO.getWords());
    }
}
