package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.charm.CharmDTO;
import com.tibiainfo.model.dto.query.CharmQueryDTO;
import com.tibiainfo.service.CharmService;
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

@RestController
@RequestMapping("/charm")
public class CharmController {

    @Autowired
    CharmService charmService;

    @GetMapping
    @ApiOperation(value = "Returns a page of charm")
    public PageSupportDTO<CharmDTO> getCharm(@Valid CharmQueryDTO queryDto) {
        return charmService.getCharms(queryDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns a specific charm")
    public CharmDTO getCharmById(@PathVariable Long id) throws NotFoundException {
        return charmService.getCharmById(id);
    }

    @GetMapping("/{id}/image")
    @ApiOperation(value = "Returns the charm's image")
    public ResponseEntity<?> getImage(@ApiParam(example = "31700") @PathVariable Long id) throws NotFoundException {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .contentType(MediaType.IMAGE_GIF)
                .body(charmService.getImage(id));

    }
}
