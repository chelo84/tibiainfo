package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.Item;
import com.tibiainfo.model.dto.ItemQueryDto;
import com.tibiainfo.model.dto.PageSupportDto;
import com.tibiainfo.model.repository.ItemRepository;
import com.tibiainfo.model.repository.specification.ItemSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public Item getItemById(Long id) throws NotFoundException {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found"));
    }

    public PageSupportDto<Item> getItems(ItemQueryDto queryDto) {
        PageRequest of = PageRequest.of(queryDto.getPage(), queryDto.getSize());

        ItemSpecification specification = ItemSpecification.builder()
                .itemQueryDto(queryDto)
                .build();

        return new PageSupportDto<>(itemRepository.findAll(specification, of));
    }
}
