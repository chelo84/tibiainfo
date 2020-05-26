package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.outfit.OutfitDTO;
import com.tibiainfo.model.dto.query.OutfitQueryDTO;
import com.tibiainfo.model.entity.outfit.Outfit;
import com.tibiainfo.model.entity.outfit.OutfitImage;
import com.tibiainfo.model.enumeration.OutfitSex;
import com.tibiainfo.model.repository.OutfitImageRepository;
import com.tibiainfo.model.repository.OutfitRepository;
import com.tibiainfo.model.repository.specification.OutfitSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.io.BaseEncoding.base16;

@Service
public class OutfitService {

    @Autowired
    OutfitRepository outfitRepository;
    @Autowired
    OutfitImageRepository outfitImageRepository;

    @Transactional(readOnly = true)
    public OutfitDTO getOutfitById(Long id) throws NotFoundException {
        return outfitRepository.findById(id)
                .map(OutfitDTO::new)
                .orElseThrow(() -> new NotFoundException(Outfit.class));
    }

    @Transactional(readOnly = true)
    public PageSupportDTO<OutfitDTO> getOutfits(OutfitQueryDTO queryDTO) {
        PageRequest of = PageRequest.of(queryDTO.getPage(), queryDTO.getSize());

        OutfitSpecification specification = OutfitSpecification.builder()
                .queryDTO(queryDTO)
                .build();

        Page<Outfit> outfits = outfitRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                outfits.map(outfit -> new OutfitDTO(outfit, queryDTO.isExtended()))
        );
    }

    @Transactional(readOnly = true)
    public byte[] getImage(Long id, OutfitSex sex, Integer addon) throws NotFoundException {
        var outfit = this.getOutfitById(id);

        Optional<OutfitImage> outfitImage = outfitImageRepository.findFirstByOutfitIdAndSexAndAddon(
                outfit.getId(),
                sex.getSex(),
                addon
        );

        return outfitImage.map(OutfitImage::getImage)
                .filter(StringUtils::isNotBlank)
                .map(base16()::decode)
                .orElseThrow(() -> new NotFoundException(OutfitImage.class));
    }
}
