package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.achievement.AchievementDTO;
import com.tibiainfo.model.dto.npc.NpcDTO;
import com.tibiainfo.model.dto.query.AchievementQueryDTO;
import com.tibiainfo.model.dto.query.NpcQueryDTO;
import com.tibiainfo.service.NpcService;
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
@RequestMapping("/npc")
@Api(tags = {"Npc"})
@SwaggerDefinition(tags= {
        @Tag(name = "Npc", description = "Npc resource")
})
public class NpcController {

    @Autowired
    NpcService npcService;

    @GetMapping
    @ApiOperation(value = "Returns a page of npc")
    public PageSupportDTO<NpcDTO> getNpcs(@Valid NpcQueryDTO queryDTO) {
        return npcService.getNpcs(queryDTO);
    }

    @GetMapping({"/{id}"})
    @ApiOperation(value = "Return a specific npc")
    public NpcDTO getAchievementById(@PathVariable Long id) throws NotFoundException {
        return npcService.getNpcById(id);
    }



}
