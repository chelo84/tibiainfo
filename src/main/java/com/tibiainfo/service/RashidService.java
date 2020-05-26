package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.query.RashidQueryDTO;
import com.tibiainfo.model.dto.rashid.RashidDTO;
import com.tibiainfo.model.entity.rashid.Rashid;
import com.tibiainfo.model.repository.RashidRepository;
import com.tibiainfo.model.repository.specification.RashidSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RashidService {

    @Autowired
    RashidRepository rashidRepository;

    @Transactional(readOnly = true)
    public RashidDTO getRashidByDay(Long day) throws NotFoundException {
        return rashidRepository.findById(day)
                .map(RashidDTO::new)
                .orElseThrow(() -> new NotFoundException("Rashid day not found"));
    }

    @Transactional(readOnly = true)
    public PageSupportDTO<RashidDTO> getRashid(RashidQueryDTO queryDTO) {
        PageRequest of = PageRequest.of(queryDTO.getPage(), queryDTO.getSize());

        RashidSpecification specification = RashidSpecification.builder()
                .rashidQueryDTO(queryDTO)
                .build();

        Page<Rashid> rashids = rashidRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                rashids.map(rashid -> new RashidDTO(rashid, queryDTO.isExtended()))
        );
    }
}
