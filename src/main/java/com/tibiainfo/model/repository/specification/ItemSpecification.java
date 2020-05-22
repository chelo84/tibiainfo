package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.query.ItemQueryDTO;
import com.tibiainfo.model.entity.Item;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ItemSpecification extends TibiaInfoSpecification<Item> {

    ItemQueryDTO itemQueryDto;

    @Override
    public void instructions() {
        equal("type", itemQueryDto.getType())
                .equal("name", itemQueryDto.getName());
    }

}