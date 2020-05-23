package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.query.ItemQueryDTO;
import com.tibiainfo.model.entity.item.Item;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ItemSpecification extends TibiaInfoSpecification<Item> {

    ItemQueryDTO itemQueryDto;

    @Override
    public void instructions() {
        equalIgnoreCase("type", itemQueryDto.getType())
                .equalIgnoreCase("name", itemQueryDto.getName());
    }

}