package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.dto.query.QuestQueryDTO;
import com.tibiainfo.model.entity.quest.Quest;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class QuestSpecification extends TibiaInfoSpecification<Quest> {

    QuestQueryDTO queryDto;

    @Override
    public void instructions() {
        equalIgnoreCase("name", queryDto.getName());
    }

}
