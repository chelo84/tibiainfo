package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.item.ItemDTO;
import com.tibiainfo.model.dto.query.ItemQueryDTO;
import com.tibiainfo.model.entity.Item;
import com.tibiainfo.model.repository.ItemRepository;
import com.tibiainfo.model.repository.specification.ItemSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.io.BaseEncoding.base16;

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
        var item = this.getItemById(id);

        String imageStr = itemRepository.getImageById(item.getId());

        return Optional.of(imageStr).filter(StringUtils::isNotBlank)
                .map(base16()::decode)
                .orElse(null);
    }
}
