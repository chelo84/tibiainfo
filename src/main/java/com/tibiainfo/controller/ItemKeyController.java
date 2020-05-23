package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.item.key.ItemKeyDTO;
import com.tibiainfo.model.dto.query.ItemKeyQueryDTO;
import com.tibiainfo.service.ItemKeyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/keys")
@SwaggerDefinition(tags = {
        @Tag(name = "Key", description = "Key Resources")
})
@Api(tags = {"Key"})
public class ItemKeyController {

    @Autowired
    ItemKeyService itemKeyService;

    @GetMapping
    @ApiOperation(value = "Returns a page of keys")
    public PageSupportDTO<ItemKeyDTO> getItemKeys(@Valid ItemKeyQueryDTO queryDto) {
        return itemKeyService.getItemKeys(queryDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns a specific key")
    public ItemKeyDTO getItemKeyById(@PathVariable Long id) throws NotFoundException {
        return itemKeyService.getItemKeyById(id);
    }

}
