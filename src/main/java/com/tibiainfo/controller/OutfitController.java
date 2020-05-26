package com.tibiainfo.controller;

import com.tibiainfo.config.CaseInsensitiveEnumEditor;
import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.outfit.OutfitDTO;
import com.tibiainfo.model.dto.outfit.OutfitQuestDTO;
import com.tibiainfo.model.dto.query.OutfitQueryDTO;
import com.tibiainfo.model.enumeration.OutfitSex;
import com.tibiainfo.service.OutfitService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

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
    public OutfitDTO getOutfitById(@PathVariable @ApiParam(example = "80934") Long id) throws NotFoundException {
        return outfitService.getOutfitById(id);
    }

    @GetMapping("/{id}/image/{addon}")
    @ApiOperation(value = "Returns the outfit's image")
    public ResponseEntity<?> getImage(@PathVariable @ApiParam(required = true, example = "80934") Long id,
                                      @PathVariable @ApiParam(required = true, example = "1", type = "integer", allowableValues = "0,1,2,3") Integer addon,
                                      @RequestParam(value = "sex") @ApiParam(required = true, example = "male", allowableValues = "male, female") @Pattern(regexp = "(male)|(female)", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Sex must be male or female") @Valid OutfitSex sex) throws NotFoundException {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .contentType(MediaType.IMAGE_GIF)
                .body(outfitService.getImage(id, sex, addon));
    }

    @GetMapping("/{id}/quests")
    @ApiOperation(value = "Returns the specified outfit's quest")
    public List<OutfitQuestDTO> getQuests(@PathVariable @ApiParam(required = true, example = "8575") Long id) throws NotFoundException {
        return outfitService.getQuests(id);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(OutfitSex.class, new CaseInsensitiveEnumEditor(OutfitSex.class));
    }

}
