package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.query.ItemKeyQueryDTO;
import com.tibiainfo.model.entity.item.key.ItemKey;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ItemKeySpecification extends TibiaInfoSpecification<ItemKey> {

    ItemKeyQueryDTO queryDTO;

    @Override
    public void instructions() {
        equal("number", queryDTO.getNumber());
    }

}
