package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.AchievementQueryDTO;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.achievement.AchievementDTO;
import com.tibiainfo.service.AchievementService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/achievement")
public class AchievementController {

    @Autowired
    AchievementService achievementService;

    @GetMapping
    @ApiOperation(value = "Returns a page of achievement")
    public PageSupportDTO<AchievementDTO> getAchievement(@Valid AchievementQueryDTO queryDTO) {
        return achievementService.getAchievement(queryDTO);
    }

    @GetMapping({"/{id}"})
    @ApiOperation(value = "Return a specific achievement")
    public AchievementDTO getAchievementById(@PathVariable Long id) throws NotFoundException {
        return achievementService.getAchievementById(id);
    }

}
