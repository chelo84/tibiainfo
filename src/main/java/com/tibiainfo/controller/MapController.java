package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.map.MapDTO;
import com.tibiainfo.model.dto.query.MapQueryDTO;
import com.tibiainfo.service.MapService;
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
@RequestMapping("/map")
public class MapController {

    @Autowired
    MapService mapService;

    @GetMapping
    @ApiOperation(value = "Return a page of map")
    public PageSupportDTO<MapDTO> getMap(@Valid MapQueryDTO queryDTO) {

        return mapService.getMaps(queryDTO);
    }

    @GetMapping("/{z}")
    @ApiOperation(value = "returns a specific z")
    public MapDTO getMapByZ(@PathVariable Long z) throws NotFoundException {

        return mapService.getMapByZ(z);
    }

    @GetMapping("/{z}/image")
    @ApiOperation(value = "Return the map image")
    public ResponseEntity<?> getImage(@ApiParam(example = "2") @PathVariable Long z) throws NotFoundException {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .contentType(MediaType.IMAGE_GIF)
                .body(mapService.getImage(z));

    }


}
