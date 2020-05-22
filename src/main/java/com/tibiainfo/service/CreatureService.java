package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.CreatureQueryDTO;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.creature.CreatureDTO;
import com.tibiainfo.model.dto.creature.CreatureDropDTO;
import com.tibiainfo.model.entity.creature.Creature;
import com.tibiainfo.model.repository.CreatureDropRepository;
import com.tibiainfo.model.repository.CreatureRepository;
import com.tibiainfo.model.repository.specification.CreatureSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.io.BaseEncoding.base16;

@Service
public class CreatureService {

    @Autowired
    CreatureRepository creatureRepository;

    @Autowired
    CreatureDropRepository creatureDropRepository;

    public CreatureDTO getCreatureById(Long id) throws NotFoundException {
        return creatureRepository.findById(id)
                .map(CreatureDTO::new)
                .orElseThrow(() -> new NotFoundException("Creature not found"));
    }

    public PageSupportDTO<CreatureDTO> getCreatures(CreatureQueryDTO queryDto) {
        PageRequest of = PageRequest.of(queryDto.getPage(), queryDto.getSize());

        CreatureSpecification specification = CreatureSpecification.builder()
                .creatureQueryDto(queryDto)
                .build();

        Page<Creature> creatures = creatureRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                creatures.map(CreatureDTO::new)
        );

    }

    public byte[] getImage(Long id) throws NotFoundException {
        var creature = this.getCreatureById(id);

        String imageStr = creatureRepository.getImageById(creature.getId());

        return Optional.of(imageStr).filter(StringUtils::isNotBlank)
                .map(base16()::decode)
                .orElse(null);

    }


    public List<CreatureDropDTO> getDrops(Long id) throws NotFoundException {
        var creature = this.getCreatureById(id);

        return creatureDropRepository.findAllByCreatureId(creature.getId())
                .stream()
                .map(CreatureDropDTO::new)
                .collect(Collectors.toList());
    }
}
