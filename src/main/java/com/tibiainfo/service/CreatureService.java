package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.creature.CreatureDTO;
import com.tibiainfo.model.dto.query.CreatureQueryDTO;
import com.tibiainfo.model.entity.creature.Creature;
import com.tibiainfo.model.repository.CreatureDropRepository;
import com.tibiainfo.model.repository.CreatureRepository;
import com.tibiainfo.model.repository.specification.CreatureSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.io.BaseEncoding.base16;

@Service
public class CreatureService {

    @Autowired
    CreatureRepository creatureRepository;

    @Autowired
    CreatureDropRepository creatureDropRepository;

    @Transactional(readOnly = true)
    public CreatureDTO getCreatureById(Long id) throws NotFoundException {
        return creatureRepository.findById(id)
                .map(CreatureDTO::new)
                .orElseThrow(() -> new NotFoundException(Creature.class));
    }

    @Transactional(readOnly = true)
    public PageSupportDTO<CreatureDTO> getCreatures(CreatureQueryDTO queryDTO) {
        PageRequest of = PageRequest.of(queryDTO.getPage(), queryDTO.getSize());

        CreatureSpecification specification = CreatureSpecification.builder()
                .creatureQueryDto(queryDTO)
                .build();

        Page<Creature> creatures = creatureRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                creatures.map(creature -> new CreatureDTO(creature, queryDTO.isExtended()))
        );

    }

    @Transactional(readOnly = true)
    public byte[] getImage(Long id) throws NotFoundException {
        var creature = this.getCreatureById(id);

        String imageStr = creatureRepository.getImageById(creature.getId());

        return Optional.of(imageStr).filter(StringUtils::isNotBlank)
                .map(base16()::decode)
                .orElse(null);

    }

}
