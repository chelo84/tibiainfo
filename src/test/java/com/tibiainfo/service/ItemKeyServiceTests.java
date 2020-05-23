package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.item.key.ItemKeyDTO;
import com.tibiainfo.model.dto.query.ItemKeyQueryDTO;
import com.tibiainfo.model.dto.query.ItemKeyQueryDTO.ItemKeyQueryDTOBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemKeyServiceTests {

    private final Long EXISTING_KEY = 4407L;
    private final Long NON_EXISTING_KEY = -1L;

    private final Integer EXISTING_KEY_NUMBER = 3142;

    private final ItemKeyQueryDTOBuilder<?, ?> QUERY_DTO_BUILDER;
    @Autowired
    private ItemKeyService itemKeyService;

    {
        QUERY_DTO_BUILDER = ItemKeyQueryDTO.builder()
                .page(0)
                .size(10);
    }

    @Test
    public void testGetItemKeys() {
        PageSupportDTO<ItemKeyDTO> items = itemKeyService.getItemKeys(QUERY_DTO_BUILDER.build());

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
    public void testGetItemKeysOfNumber() {
        PageSupportDTO<ItemKeyDTO> items = itemKeyService.getItemKeys(
                QUERY_DTO_BUILDER.number(Optional.of(EXISTING_KEY_NUMBER)).build()
        );

        assertNotNull(items);
        assertNotNull(items.getContent());
        assertFalse(items.isEmpty());
        assertTrue(
                items.getContent()
                        .stream()
                        .allMatch(item -> item.getNumber().equals(EXISTING_KEY_NUMBER))
        );
    }

    @Test
    public void testGetItemKeyById() throws NotFoundException {
        ItemKeyDTO fetchedItem = itemKeyService.getItemKeyById(EXISTING_KEY);

        assertNotNull(fetchedItem);
        assertEquals(EXISTING_KEY, fetchedItem.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetItemKeyThatDoesNotExist() throws NotFoundException {
        itemKeyService.getItemKeyById(NON_EXISTING_KEY);
    }

}
