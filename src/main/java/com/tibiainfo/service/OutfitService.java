package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.outfit.OutfitDTO;
import com.tibiainfo.model.dto.query.OutfitQueryDTO;
import com.tibiainfo.model.entity.outfit.Outfit;
import com.tibiainfo.model.repository.OutfitRepository;
import com.tibiainfo.model.repository.specification.OutfitSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OutfitService {

    @Autowired
    OutfitRepository outfitRepository;

    @Transactional(readOnly = true)
    public OutfitDTO getOutfitById(Long id) throws NotFoundException {
        return outfitRepository.findById(id)
                .map(OutfitDTO::new)
                .orElseThrow(() -> new NotFoundException("Outfit not found"));
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
    public byte[] getImage(Long id) throws NotFoundException {
        var outfit = this.getOutfitById(id);

        return null;
//        String imageStr = outfitRepository.getImageById(outfit.getId());
//
//        return Optional.of(imageStr).filter(StringUtils::isNotBlank)
//                .map(base16()::decode)
//                .orElse(null);
    }
}
