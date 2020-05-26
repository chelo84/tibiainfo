package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.query.MountQueryDTO;
import com.tibiainfo.model.entity.mount.Mount;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MountSpecification extends TibiaInfoSpecification<Mount> {

    MountQueryDTO mountQueryDTO;

    @Override
    public void instructions() {
        equalIgnoreCase("name", mountQueryDTO.getName());
    }
}
