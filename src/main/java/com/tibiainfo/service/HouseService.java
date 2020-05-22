package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.house.HouseDTO;
import com.tibiainfo.model.dto.query.HouseQueryDTO;
import com.tibiainfo.model.entity.house.House;
import com.tibiainfo.model.repository.HouseRepository;
import com.tibiainfo.model.repository.specification.HouseSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class HouseService {

    @Autowired
    HouseRepository houseRepository;

    public HouseDTO getHouseById(Long id) throws NotFoundException {
        return houseRepository.findById(id)
                .map(HouseDTO::new)
                .orElseThrow(() -> new NotFoundException("House not found"));
    }

    public PageSupportDTO<HouseDTO> getHouses(HouseQueryDTO queryDto) {
        PageRequest of = PageRequest.of(queryDto.getPage(), queryDto.getSize());

        HouseSpecification specification = HouseSpecification.builder()
                .queryDTO(queryDto)
                .build();

        Page<House> houses = houseRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                houses.map(HouseDTO::new)
        );

    }
}
