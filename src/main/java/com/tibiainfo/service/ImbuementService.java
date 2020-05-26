package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.imbuement.ImbuementDTO;
import com.tibiainfo.model.dto.query.ImbuementQueryDTO;
import com.tibiainfo.model.entity.imbuement.Imbuement;
import com.tibiainfo.model.repository.ImbuementRepository;
import com.tibiainfo.model.repository.specification.ImbuementSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.io.BaseEncoding.base16;

@Service
public class ImbuementService {

    @Autowired
    ImbuementRepository imbuementRepository;

    @Transactional(readOnly = true)
    public ImbuementDTO getImbuementById(Long id) throws NotFoundException {
        return imbuementRepository.findById(id)
                .map(ImbuementDTO::new)
                .orElseThrow(() -> new NotFoundException(Imbuement.class));
    }

    @Transactional(readOnly = true)
    public PageSupportDTO<ImbuementDTO> getImbuements(ImbuementQueryDTO queryDTO) {
        PageRequest of = PageRequest.of(queryDTO.getPage(), queryDTO.getSize());

        ImbuementSpecification specification = ImbuementSpecification.builder()
                .imbuementQueryDTO(queryDTO)
                .build();

        Page<Imbuement> imbuements = imbuementRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                imbuements.map(imbuement -> new ImbuementDTO(imbuement, queryDTO.isExtended()))
        );
    }

    @Transactional(readOnly = true)
    public byte[] getImage(Long id) throws NotFoundException {
        var charm = this.getImbuementById(id);

        String imageStr = imbuementRepository.getImageById(charm.getId());

        return Optional.of(imageStr).filter(StringUtils::isNotBlank)
                .map(base16()::decode)
                .orElse(null);
    }

}
