package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.item.ItemDTO;
import com.tibiainfo.model.dto.query.ItemQueryDTO;
import com.tibiainfo.model.dto.query.ItemQueryDTO.ItemQueryDTOBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemServiceTests {

    private final Long EXISTING_ITEM = 2303L;
    private final Long NON_EXISTING_ITEM = -1L;
    private final Long ITEM_WITH_SOUND = 2884L;

    private final String BOOTS_TYPE = "Boots";

    private final ItemQueryDTOBuilder<?, ?> QUERY_DTO_BUILDER;
    @Autowired
    private ItemService itemService;

    {
        QUERY_DTO_BUILDER = ItemQueryDTO.builder()
                .page(0)
                .size(10);
    }

    @Test
    public void testGetItems() {
        PageSupportDTO<ItemDTO> items = itemService.getItems(QUERY_DTO_BUILDER.build());

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
        PageSupportDTO<ItemDTO> items = itemService.getItems(
                QUERY_DTO_BUILDER.type(Optional.of(BOOTS_TYPE)).build()
        );

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
        ItemDTO fetchedItem = itemService.getItemById(EXISTING_ITEM);

        assertNotNull(fetchedItem);
        assertEquals(EXISTING_ITEM, fetchedItem.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetItemThatDoesNotExist() throws NotFoundException {
        itemService.getItemById(NON_EXISTING_ITEM);
    }

    @Test
    public void testGetItemImage() throws NotFoundException {
        byte[] image = itemService.getImage(EXISTING_ITEM);

        assertNotNull(image);
    }

    @Test(expected = NotFoundException.class)
    public void testGetItemImageForAnItemThatDoesNotExist() throws NotFoundException {
        itemService.getImage(NON_EXISTING_ITEM);
    }

    @Test
    public void testGetItemByNameAndType() {
        itemService.getItems(
                QUERY_DTO_BUILDER.type(Optional.of(BOOTS_TYPE))
                        .name(Optional.of("Boots of Hste"))
                        .build()
        );
    }

    @Test
    public void testGetItemsWithNonExtendedJson() {
        PageSupportDTO<ItemDTO> items = itemService.getItems(
                QUERY_DTO_BUILDER.build()
        );

        assertNotNull(items);
        assertNotNull(items.getContent());
        assertFalse(items.isEmpty());
        assertTrue(
                items.getContent()
                        .stream()
                        .map(ItemDTO::getValueSell)
                        .allMatch(Objects::isNull)
        );
    }

    @Test
    public void testGetItemsWithExtendedJson() {
        PageSupportDTO<ItemDTO> items = itemService.getItems(
                QUERY_DTO_BUILDER.extended(true).build()
        );

        assertNotNull(items);
        assertNotNull(items.getContent());
        assertFalse(items.isEmpty());
        assertTrue(
                items.getContent()
                        .stream()
                        .map(ItemDTO::getValueSell)
                        .anyMatch(Objects::nonNull)
        );
    }

    @Test
    public void testItemAttributes() throws NotFoundException {
        ItemDTO fetchedItem = itemService.getItemById(EXISTING_ITEM);

        assertNotNull(fetchedItem);
        assertNotNull(fetchedItem.getAttributes());
        assertFalse(fetchedItem.getAttributes().isEmpty());
    }

    @Test
    public void testItemSounds() throws NotFoundException {
        ItemDTO fetchedItem = itemService.getItemById(ITEM_WITH_SOUND);

        assertNotNull(fetchedItem);
        assertNotNull(fetchedItem.getAttributes());
        assertFalse(fetchedItem.getAttributes().isEmpty());
    }

}
