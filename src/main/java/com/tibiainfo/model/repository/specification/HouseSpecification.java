package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.query.HouseQueryDTO;
import com.tibiainfo.model.entity.house.House;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class HouseSpecification extends TibiaInfoSpecification<House> {

    HouseQueryDTO queryDTO;

    @Override
    public void instructions() {
        like("name", queryDTO.getName())
                .like("city", queryDTO.getCity())
                .like("street", queryDTO.getStreet());
    }

}
