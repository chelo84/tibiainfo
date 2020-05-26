package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.achievement.AchievementDTO;
import com.tibiainfo.model.dto.query.AchievementQueryDTO;
import com.tibiainfo.model.entity.achievement.Achievement;
import com.tibiainfo.model.repository.AchievementRepository;
import com.tibiainfo.model.repository.specification.AchievementSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AchievementService {

    @Autowired
    AchievementRepository achievementRepository;

    public AchievementDTO getAchievementById(Long id) throws NotFoundException {
        return achievementRepository.findById(id)
                .map(AchievementDTO::new)
                .orElseThrow(() -> new NotFoundException(Achievement.class));
    }

    public PageSupportDTO<AchievementDTO> getAchievements(AchievementQueryDTO queryDTO) {
        PageRequest of = PageRequest.of(queryDTO.getPage(), queryDTO.getSize());

        AchievementSpecification specification = AchievementSpecification.builder()
                .achievementQueryDTO(queryDTO)
                .build();

        Page<Achievement> achievement = achievementRepository.findAll(specification, of);

        return new PageSupportDTO<>(
                achievement.map(ach -> new AchievementDTO(ach, queryDTO.isExtended()))
        );

    }
}
