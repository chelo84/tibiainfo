package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.query.RashidQueryDTO;
import com.tibiainfo.model.entity.rashid.Rashid;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class RashidSpecification extends TibiaInfoSpecification<Rashid> {

    RashidQueryDTO rashidQueryDTO;

    @Override
    public void instructions() {
        equalIgnoreCase("city", rashidQueryDTO.getCity());
    }

}
