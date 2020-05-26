package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.mount.MountDTO;
import com.tibiainfo.model.dto.query.MountQueryDTO;
import com.tibiainfo.model.entity.mount.Mount;
import com.tibiainfo.model.repository.specification.MountRepository;
import com.tibiainfo.model.repository.specification.MountSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.io.BaseEncoding.base16;

@Service
public class MountService {

    @Autowired
    MountRepository mountRepository;

    @Transactional(readOnly = true)
    public MountDTO getMountById(Long id) throws NotFoundException {
        return mountRepository.findById(id)
                .map(MountDTO::new)
                .orElseThrow(() -> new NotFoundException("Mount not found"));
    }

    @Transactional(readOnly = true)
    public PageSupportDTO<MountDTO> getMounts(MountQueryDTO queryDTO) {
        PageRequest of = PageRequest.of(queryDTO.getPage(), queryDTO.getSize());

        MountSpecification specification = MountSpecification.builder()
                .mountQueryDTO(queryDTO)
                .build();

        Page<Mount> mounts = mountRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                mounts.map(mount -> new MountDTO(mount, queryDTO.isExtended()))
        );
    }

    @Transactional(readOnly = true)
    public byte[] getImage(Long id) throws NotFoundException {
        var mount = this.getMountById(id);

        String imageStr = mountRepository.getImageById(mount.getId());

        return Optional.of(imageStr).filter(StringUtils::isNotBlank)
                .map(base16()::decode)
                .orElse(null);
    }
}
