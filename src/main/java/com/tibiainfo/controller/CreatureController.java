package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.CreatureQueryDTO;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.creature.CreatureDTO;
import com.tibiainfo.model.entity.Creature;
import com.tibiainfo.service.CreatureService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/creatures")
public class CreatureController {

    @Autowired
    CreatureService creatureService;

    @GetMapping
    @ApiOperation(value = "Returns a page of creatures")
    public PageSupportDTO<CreatureDTO> getCreatures(@Valid CreatureQueryDTO queryDto) {
        return creatureService.getCreatures(queryDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns a specific creature")
    public Creature getCreatureById(@PathVariable long id) throws NotFoundException {
        return creatureService.getCreatureById(id);
    }
}
