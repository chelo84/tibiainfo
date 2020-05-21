package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.CreatureQueryDTO;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.item.CreatureDTO;
import com.tibiainfo.model.entity.Creature;
import com.tibiainfo.model.repository.CreatureRepository;
import com.tibiainfo.model.repository.specification.CreatureSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CreatureService {

    @Autowired
    CreatureRepository creatureRepository;

    public Creature getCreatureById(Long id) throws NotFoundException {
        return creatureRepository.findById(id)
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


}
