package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.CreatureQueryDTO;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.creature.CreatureDTO;
import com.tibiainfo.model.entity.creature.Creature;
import com.tibiainfo.model.entity.creature.CreatureDrop;
import com.tibiainfo.service.CreatureService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/{id}/image")
    @ApiOperation(value = "Returns the creature's image")
    public ResponseEntity<?> getImage(@ApiParam(example = "1244") @PathVariable Long id) {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .contentType(MediaType.IMAGE_GIF)
                .body(creatureService.getImage(id));

    }

    @GetMapping("/{id}/drops")
    @ApiOperation(value = "Returns the creature's drops data")
    public List<CreatureDrop> getDrops(@ApiParam(example = "1244") @PathVariable Long id) {
        // TODO change from entity to a DTO
        return creatureService.getDrops(id);

    }
}
