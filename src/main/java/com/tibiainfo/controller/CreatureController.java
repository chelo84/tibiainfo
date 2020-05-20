package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.Creature;
import com.tibiainfo.model.dto.CreatureQueryDto;
import com.tibiainfo.model.dto.PageSupportDto;
import com.tibiainfo.service.CreatureService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/creature")
public class CreatureController {

    @Autowired
    CreatureService creatureService;

    @GetMapping
    @ApiOperation(value = "Returns a page of creatures")
    public PageSupportDto<Creature> getCreature(@Valid CreatureQueryDto queryDto) {
        return creatureService.getCreature(queryDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns a specific creature")
    public Creature getCreatureById (@PathVariable long id) throws NotFoundException{
        return creatureService.getCreature(id);
    }
}
