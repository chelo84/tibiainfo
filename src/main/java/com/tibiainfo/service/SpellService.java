package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.query.SpellQueryDTO;
import com.tibiainfo.model.dto.spell.SpellDTO;
import com.tibiainfo.model.entity.spell.Spell;
import com.tibiainfo.model.repository.SpellRepository;
import com.tibiainfo.model.repository.specification.SpellSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.io.BaseEncoding.base16;

@Service
public class SpellService {

    @Autowired
    SpellRepository spellRepository;

    @Transactional(readOnly = true)
    public SpellDTO getSpellById(Long id) throws NotFoundException {
        return spellRepository.findById(id)
                .map(SpellDTO::new)
                .orElseThrow(() -> new NotFoundException(Spell.class));
    }

    @Transactional(readOnly = true)
    public PageSupportDTO<SpellDTO> getSpells(SpellQueryDTO queryDTO) {
        PageRequest of = PageRequest.of(queryDTO.getPage(), queryDTO.getSize());

        SpellSpecification specification = SpellSpecification.builder()
                .spellQueryDTO(queryDTO)
                .build();

        Page<Spell> spells = spellRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                spells.map(spell -> new SpellDTO(spell, queryDTO.isExtended()))
        );
    }

    @Transactional(readOnly = true)
    public byte[] getImage(Long id) throws NotFoundException {
        var spell = this.getSpellById(id);

        String imageStr = spellRepository.getImageById(spell.getId());

        return Optional.of(imageStr).filter(StringUtils::isNotBlank)
                .map(base16()::decode)
                .orElse(null);

    }
}
