package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.Item;
import com.tibiainfo.model.dto.ItemQueryDto;
import com.tibiainfo.model.dto.PageSupportDto;
import com.tibiainfo.service.ItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    @ApiOperation(value = "Returns a page of items")
    public PageSupportDto<Item> getItems(@Valid ItemQueryDto queryDto) {
        return itemService.getItems(queryDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns a specific item")
    public Item getItemById(@PathVariable Long id) throws NotFoundException {
        return itemService.getItemById(id);
    }

}
