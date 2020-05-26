package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.map.MapDTO;
import com.tibiainfo.model.dto.query.MapQueryDTO;
import com.tibiainfo.model.entity.map.Map;
import com.tibiainfo.model.repository.MapRepository;
import com.tibiainfo.model.repository.specification.MapSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.io.BaseEncoding.base16;

@Service
public class MapService {

    @Autowired
    MapRepository mapRepository;

    public MapDTO getMapByZ(Long z) throws NotFoundException {

        return mapRepository.findById(z)
                .map(MapDTO::new)
                .orElseThrow(() -> new NotFoundException(Map.class));
    }

    public PageSupportDTO<MapDTO> getMaps(MapQueryDTO queryDTO) {
        PageRequest of = PageRequest.of(queryDTO.getPage(), queryDTO.getSize());

        MapSpecification specification = MapSpecification.builder()
                .mapQueryDTO(queryDTO)
                .build();

        Page<Map> maps = mapRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                maps.map(map -> new MapDTO(map, queryDTO.isExtended())));
    }

    public byte[] getImage(Long z) throws NotFoundException {
        var map = this.getMapByZ(z);

        String imageStr = mapRepository.getImageByZ(map.getZ());

        return Optional.of(imageStr).filter(StringUtils::isNotBlank)
                .map(base16()::decode)
                .orElse(null);
    }
}
