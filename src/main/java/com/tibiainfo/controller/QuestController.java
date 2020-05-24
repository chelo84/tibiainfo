package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.query.QuestQueryDTO;
import com.tibiainfo.model.dto.quest.QuestDTO;
import com.tibiainfo.service.QuestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/quests")
@SwaggerDefinition(tags = {
        @Tag(name = "Quest", description = "Quest Resources")
})
@Api(tags = {"Quest"})
public class QuestController {

    @Autowired
    QuestService questService;

    @GetMapping
    @ApiOperation(value = "Returns a page of quests")
    public PageSupportDTO<QuestDTO> getQuests(@Valid QuestQueryDTO queryDto) {
        return questService.getQuests(queryDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns a specific quest")
    public QuestDTO getQuestById(@PathVariable Long id) throws NotFoundException {
        return questService.getQuestById(id);
    }

}
