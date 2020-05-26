package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.item.key.ItemKeyDTO;
import com.tibiainfo.model.dto.query.ItemKeyQueryDTO;
import com.tibiainfo.model.entity.item.key.ItemKey;
import com.tibiainfo.model.repository.ItemKeyRepository;
import com.tibiainfo.model.repository.specification.ItemKeySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemKeyService {

    @Autowired
    ItemKeyRepository itemKeyRepository;

    @Transactional(readOnly = true)
    public ItemKeyDTO getItemKeyById(Long id) throws NotFoundException {
        return itemKeyRepository.findById(id)
                .map(ItemKeyDTO::new)
                .orElseThrow(() -> new NotFoundException(ItemKey.class));
    }

    @Transactional(readOnly = true)
    public PageSupportDTO<ItemKeyDTO> getItemKeys(ItemKeyQueryDTO queryDTO) {
        PageRequest of = PageRequest.of(queryDTO.getPage(), queryDTO.getSize());

        ItemKeySpecification specification = ItemKeySpecification.builder()
                .queryDTO(queryDTO)
                .build();

        Page<ItemKey> items = itemKeyRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                items.map(item -> new ItemKeyDTO(item, queryDTO.isExtended()))
        );
    }

}
