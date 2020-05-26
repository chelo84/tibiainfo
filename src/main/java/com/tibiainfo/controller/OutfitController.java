package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.outfit.OutfitDTO;
import com.tibiainfo.model.dto.query.OutfitQueryDTO;
import com.tibiainfo.service.OutfitService;
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
@RequestMapping("/outfits")
@SwaggerDefinition(tags = {
        @Tag(name = "Outfit", description = "Outfit Resources")
})
@Api(tags = {"Outfit"})
public class OutfitController {

    @Autowired
    OutfitService outfitService;

    @GetMapping
    @ApiOperation(value = "Returns a page of outfits")
    public PageSupportDTO<OutfitDTO> getOutfits(@Valid OutfitQueryDTO queryDto) {
        return outfitService.getOutfits(queryDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns a specific outfit")
    public OutfitDTO getOutfitById(@PathVariable Long id) throws NotFoundException {
        return outfitService.getOutfitById(id);
    }

    @GetMapping("/{id}/image")
    @ApiOperation(value = "Returns the outfit's image")
    public ResponseEntity<?> getImage(@PathVariable Long id) throws NotFoundException {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .contentType(MediaType.IMAGE_GIF)
                .body(outfitService.getImage(id));
    }

}
