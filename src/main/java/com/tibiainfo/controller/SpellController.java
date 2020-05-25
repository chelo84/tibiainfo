package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.query.SpellQueryDTO;
import com.tibiainfo.model.dto.spell.SpellDTO;
import com.tibiainfo.service.SpellService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/spells")
@SwaggerDefinition(tags = {
        @Tag(name = "Spell", description = "Spell Resources")
})
@Api(tags = {"Spell"})
public class SpellController {

    @Autowired
    SpellService spellService;

    @GetMapping()
    @ApiOperation(value = "Return a page of spells")
    public PageSupportDTO<SpellDTO> getSpells(@Valid SpellQueryDTO queryDTO) {
        return spellService.getSpells(queryDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns a specific spell")
    public SpellDTO getSepllById(@PathVariable Long id) throws NotFoundException {
        return spellService.getSpellById(id);
    }

    @GetMapping("/{id}/image")
    @ApiOperation(value = "Return the spell's image")
    public ResponseEntity<?> getImage(@PathVariable Long id) throws NotFoundException {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .contentType(MediaType.IMAGE_GIF)
                .body(spellService.getImage(id));
    }
}
