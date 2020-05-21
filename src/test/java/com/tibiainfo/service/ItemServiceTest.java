package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.Item;
import com.tibiainfo.model.dto.ItemQueryDto;
import com.tibiainfo.model.dto.PageSupportDto;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemServiceTest {

    private final Long EXISTING_ITEM = 2303L;
    private final Long NON_EXISTING_ITEM = -1L;

    private final String BOOTS_TYPE = "Boots";

    @Autowired
    private ItemService itemService;

    @Test
    public void testGetItems() {
        ItemQueryDto itemQueryDto = ItemQueryDto.builder()
                .type(Optional.empty())
                .page(0)
                .size(10)
                .build();

        PageSupportDto<Item> items = itemService.getItems(itemQueryDto);

        assertNotNull(items);
        assertNotNull(items.getContent());
        assertNotNull(items.getNumberOfElements());
        assertNotNull(items.getTotalElements());
        assertNotNull(items.getTotalPages());

        assertFalse(items.isEmpty());
        assertFalse(items.getContent().isEmpty());
        assertFalse(items.isLast());
    }

    @Test
    public void testGetItemsOfType() {
        ItemQueryDto itemQueryDto = ItemQueryDto.builder()
                .type(Optional.of(BOOTS_TYPE))
                .page(0)
                .size(10)
                .build();

        PageSupportDto<Item> items = itemService.getItems(itemQueryDto);

        assertNotNull(items);
        assertNotNull(items.getContent());
        assertFalse(items.isEmpty());
        assertTrue(
                items.getContent()
                        .stream()
                        .allMatch(item -> item.getType().equalsIgnoreCase(BOOTS_TYPE))
        );
    }

    @Test
    public void testGetItemById() throws NotFoundException {
        Item fetchedItem = itemService.getItemById(EXISTING_ITEM);

        assertNotNull(fetchedItem);
        assertEquals(EXISTING_ITEM, fetchedItem.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetItemThatDoesNotExist() throws NotFoundException {
        itemService.getItemById(NON_EXISTING_ITEM);
    }

}
