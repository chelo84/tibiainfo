package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.imbuement.ImbuementDTO;
import com.tibiainfo.model.dto.query.ImbuementQueryDTO;
import com.tibiainfo.service.ImbuementService;
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
@RequestMapping("/imbuements")
public class ImbuementController {

    @Autowired
    ImbuementService imbuementService;

    @GetMapping
    @ApiOperation(value = "Returns a page of imbuement")
    public PageSupportDTO<ImbuementDTO> getCharm(@Valid ImbuementQueryDTO queryDto) {

        return imbuementService.getImbuements(queryDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns a specific imbuement")
    public ImbuementDTO getImbuementById(@PathVariable Long id) throws NotFoundException {

        return imbuementService.getImbuementById(id);
    }

    @GetMapping("/{id}/image")
    @ApiOperation(value = "Returns a specific imbuement's image")
    public ResponseEntity<?> getImage(@ApiParam(example = "80288") @PathVariable Long id) throws NotFoundException {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .contentType(MediaType.IMAGE_GIF)
                .body(imbuementService.getImage(id));
    }
}
