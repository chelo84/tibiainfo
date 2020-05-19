package com.tibiainfo.controller;

import com.tibiainfo.model.Item;
import com.tibiainfo.model.dto.ItemQueryDto;
import com.tibiainfo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public Page<Item> getItems(ItemQueryDto queryDto) {

        return itemService.getItems(queryDto);
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable Long id) {

        return itemService.getItem(id);
    }

}
