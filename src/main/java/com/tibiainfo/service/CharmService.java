package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.charm.CharmDTO;
import com.tibiainfo.model.dto.query.CharmQueryDTO;
import com.tibiainfo.model.entity.charm.Charm;
import com.tibiainfo.model.repository.CharmRepository;
import com.tibiainfo.model.repository.specification.CharmSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.io.BaseEncoding.base16;

@Service
public class CharmService {

    @Autowired
    CharmRepository charmRepository;

    public CharmDTO getCharmById(Long id) throws NotFoundException {
        return charmRepository.findById(id)
                .map(CharmDTO::new)
                .orElseThrow(() -> new NotFoundException("Charm not found"));
    }

    public PageSupportDTO<CharmDTO> getCharms(CharmQueryDTO queryDTO) {
        PageRequest of = PageRequest.of(queryDTO.getPage(), queryDTO.getSize());

        CharmSpecification specification = CharmSpecification.builder()
                .charmQueryDTO(queryDTO)
                .build();

        Page<Charm> charms = charmRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                charms.map(charm -> new CharmDTO(charm, queryDTO.isExtended()))
        );
    }

    public byte[] getImage(Long id) throws NotFoundException {
        var charm = this.getCharmById(id);

        String imageStr = charmRepository.getImageById(charm.getId());

        return Optional.of(imageStr).filter(StringUtils::isNotBlank)
                .map(base16()::decode)
                .orElse(null);
    }
}
