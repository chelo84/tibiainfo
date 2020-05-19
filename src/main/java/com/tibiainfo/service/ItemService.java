package com.tibiainfo.service;

import com.tibiainfo.model.Item;
import com.tibiainfo.model.dto.ItemQueryDto;
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

    public Item getItem(Long id) {
        return itemRepository.findById(id)
                .orElse(null);
    }

    public Page<Item> getItems(ItemQueryDto queryDto) {
        PageRequest of = PageRequest.of(queryDto.getPage(), queryDto.getSize());

        ItemSpecification specification = ItemSpecification.builder()
                .itemQueryDto(queryDto)
                .build();

        return itemRepository.findAll(specification, of);
    }
}
