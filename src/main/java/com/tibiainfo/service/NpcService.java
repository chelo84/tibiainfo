package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.item.ItemDTO;
import com.tibiainfo.model.dto.npc.NpcDTO;
import com.tibiainfo.model.dto.query.NpcQueryDTO;
import com.tibiainfo.model.entity.item.Item;
import com.tibiainfo.model.entity.npc.Npc;
import com.tibiainfo.model.repository.NpcRepository;
import com.tibiainfo.model.repository.specification.NpcSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.io.BaseEncoding.base16;

@Service
public class NpcService {

    @Autowired
    NpcRepository npcRepository;

    @Transactional(readOnly = true)
    public NpcDTO getNpcById(Long id) throws NotFoundException {
        return npcRepository.findById(id)
                .map(NpcDTO::new)
                .orElseThrow(() -> new NotFoundException(Item.class));
    }

    @Transactional(readOnly = true)
    public PageSupportDTO<NpcDTO> getNpcs(NpcQueryDTO queryDTO) {
        PageRequest of = PageRequest.of(queryDTO.getPage(), queryDTO.getSize());

        NpcSpecification specification = NpcSpecification.builder()
                .npcQueryDTO(queryDTO)
                .build();

        Page<Npc> npcs = npcRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                npcs.map(npc -> new NpcDTO(npc, queryDTO.isExtended()))
        );
    }

    @Transactional(readOnly = true)
    public byte[] getImage(Long id) throws NotFoundException {
        var npc = this.getNpcById(id);

        String imageStr = npcRepository.getImageById(npc.getId());

        return Optional.of(imageStr).filter(StringUtils::isNotBlank)
                .map(base16()::decode)
                .orElse(null);
    }
}
