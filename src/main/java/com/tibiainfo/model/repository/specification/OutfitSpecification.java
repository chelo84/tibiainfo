package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.query.OutfitQueryDTO;
import com.tibiainfo.model.entity.outfit.Outfit;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class OutfitSpecification extends TibiaInfoSpecification<Outfit> {

    OutfitQueryDTO queryDTO;

    @Override
    public void instructions() {
        like("name", queryDTO.getName())
                .or()
                .like("title", queryDTO.getName());
    }
}
