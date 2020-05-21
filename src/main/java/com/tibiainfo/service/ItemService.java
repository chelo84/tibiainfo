package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.ItemQueryDTO;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.item.ItemDTO;
import com.tibiainfo.model.entity.Item;
import com.tibiainfo.model.repository.ItemRepository;
import com.tibiainfo.model.repository.specification.ItemSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public ItemDTO getItemById(Long id) throws NotFoundException {
        return itemRepository.findById(id)
                .map(ItemDTO::new)
                .orElseThrow(() -> new NotFoundException("Item not found"));
    }

    public PageSupportDTO<ItemDTO> getItems(ItemQueryDTO queryDto) {
        PageRequest of = PageRequest.of(queryDto.getPage(), queryDto.getSize());

        ItemSpecification specification = ItemSpecification.builder()
                .itemQueryDto(queryDto)
                .build();

        Page<Item> items = itemRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                items.map(ItemDTO::new)
        );

    }

    public byte[] getImage(Long id) throws NotFoundException {
        return itemRepository.getImageById(id)
                .orElseThrow(() -> new NotFoundException("Item not found"));
    }
}
