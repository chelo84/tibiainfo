package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.mount.MountDTO;
import com.tibiainfo.model.dto.query.MountQueryDTO;
import com.tibiainfo.service.MountService;
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
@RequestMapping("/mounts")
@SwaggerDefinition(tags = {
        @Tag(name = "Mount", description = "Mount Resources")
})
@Api(tags = {"Mount"})
public class MountController {

    @Autowired
    MountService mountService;

    @GetMapping
    @ApiOperation(value = "Returns a page of mounts")
    public PageSupportDTO<MountDTO> getMounts(@Valid MountQueryDTO queryDTO) {
        return mountService.getMounts(queryDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns a specific mount")
    public MountDTO getMountById(@PathVariable Long id) throws NotFoundException {
        return mountService.getMountById(id);
    }

    @GetMapping("/{id}/image")
    @ApiOperation(value = "Returns the mount's image")
    public ResponseEntity<?> getImage(@PathVariable Long id) throws NotFoundException {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .contentType(MediaType.IMAGE_GIF)
                .body(mountService.getImage(id));
    }
}
