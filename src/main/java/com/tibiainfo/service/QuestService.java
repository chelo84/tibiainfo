package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.query.QuestQueryDTO;
import com.tibiainfo.model.dto.quest.QuestDTO;
import com.tibiainfo.model.entity.quest.Quest;
import com.tibiainfo.model.repository.QuestRepository;
import com.tibiainfo.model.repository.specification.QuestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestService {

    @Autowired
    QuestRepository questRepository;

    @Transactional(readOnly = true)
    public QuestDTO getQuestById(Long id) throws NotFoundException {
        return questRepository.findById(id)
                .map(QuestDTO::new)
                .orElseThrow(() -> new NotFoundException("Quest not found"));
    }

    @Transactional(readOnly = true)
    public PageSupportDTO<QuestDTO> getQuests(QuestQueryDTO queryDTO) {
        PageRequest of = PageRequest.of(queryDTO.getPage(), queryDTO.getSize());

        QuestSpecification specification = QuestSpecification.builder()
                .queryDto(queryDTO)
                .build();

        Page<Quest> quests = questRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                quests.map(quest -> new QuestDTO(quest, queryDTO.isExtended()))
        );
    }

}
