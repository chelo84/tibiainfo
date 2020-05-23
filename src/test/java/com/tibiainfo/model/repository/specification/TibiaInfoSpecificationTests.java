package com.tibiainfo.model.repository.specification;

import com.tibiainfo.model.entity.item.Item;
import com.tibiainfo.model.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.equalsAnyIgnoreCase;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TibiaInfoSpecificationTests {

    final PageRequest PAGE_REQUEST = PageRequest.of(0, 20);
    final String BOOTS_TYPE = "Boots";
    final String BOOTS_OF_HASTE = "Boots of Haste";
    final String PLATE_ARMOR = "Plate Armor";

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void testWithNoContinueInstructions() {
        TibiaInfoSpecification<Item> specification = new NoContinueInstructionsSpecification();

        Page<Item> items = itemRepository.findAll(specification, PAGE_REQUEST);

        assertNotNull(items);
        assertNotNull(items.getContent());

        assertFalse(items.isEmpty());
        assertFalse(items.getContent().isEmpty());
    }

    @Test
    public void testBootsOfHasteOrPlateArmor() {
        TibiaInfoSpecification<Item> specification = new BootsOfHasteOrPlateArmorSpecification();

        Page<Item> items = itemRepository.findAll(specification, PAGE_REQUEST);

        assertNotNull(items);
        assertNotNull(items.getContent());

        assertFalse(items.isEmpty());
        assertFalse(items.getContent().isEmpty());
        assertTrue(
                items.getContent()
                        .stream()
                        .allMatch(i -> equalsAnyIgnoreCase(i.getName(), PLATE_ARMOR, BOOTS_OF_HASTE))
        );
    }

    @Test
    public void testBootsWithNameBootsOfHaste() {
        TibiaInfoSpecification<Item> specification = new BootsWithNameBootsOfHasteSpecification();

        Page<Item> items = itemRepository.findAll(specification, PAGE_REQUEST);

        assertNotNull(items);
        assertNotNull(items.getContent());

        assertFalse(items.isEmpty());
        assertFalse(items.getContent().isEmpty());
        assertTrue(
                items.getContent()
                        .stream()
                        .allMatch(i -> i.getName().equalsIgnoreCase(BOOTS_OF_HASTE))
        );
    }

    @Test
    public void testAllBootsAndPlateArmor() {
        TibiaInfoSpecification<Item> specification = new AllBootsAndPlateArmorSpecification();

        Page<Item> items = itemRepository.findAll(specification, PageRequest.of(0, Integer.MAX_VALUE));

        assertNotNull(items);
        assertNotNull(items.getContent());

        assertFalse(items.isEmpty());
        assertFalse(items.getContent().isEmpty());
        assertTrue(
                items.getContent()
                        .stream()
                        .allMatch(i -> i.getType().equalsIgnoreCase(BOOTS_TYPE)
                                || i.getName().equalsIgnoreCase(PLATE_ARMOR)
                        )
        );
    }

    private class NoContinueInstructionsSpecification extends TibiaInfoSpecification<Item> {

        protected NoContinueInstructionsSpecification(TibiaInfoSpecificationBuilder<Item, ?, ?> b) {
            super(b);
        }

        public NoContinueInstructionsSpecification() {
        }

        @Override
        public void instructions() {
            equal("type", Optional.of(BOOTS_TYPE))
                    .equal("name", Optional.of(BOOTS_OF_HASTE));
        }

    }

    private class BootsOfHasteOrPlateArmorSpecification extends TibiaInfoSpecification<Item> {

        protected BootsOfHasteOrPlateArmorSpecification(TibiaInfoSpecificationBuilder<Item, ?, ?> b) {
            super(b);
        }

        public BootsOfHasteOrPlateArmorSpecification() {
        }

        @Override
        public void instructions() {
            equal("name", Optional.of(PLATE_ARMOR))
                    .or()
                    .equal("name", Optional.of(BOOTS_OF_HASTE));
        }

    }

    private class BootsWithNameBootsOfHasteSpecification extends TibiaInfoSpecification<Item> {

        protected BootsWithNameBootsOfHasteSpecification(TibiaInfoSpecificationBuilder<Item, ?, ?> b) {
            super(b);
        }

        public BootsWithNameBootsOfHasteSpecification() {
        }

        @Override
        public void instructions() {
            equal("type", Optional.of(BOOTS_TYPE))
                    .and()
                    .equal("name", Optional.of(BOOTS_OF_HASTE));
        }

    }

    private class AllBootsAndPlateArmorSpecification extends TibiaInfoSpecification<Item> {

        protected AllBootsAndPlateArmorSpecification(TibiaInfoSpecificationBuilder<Item, ?, ?> b) {
            super(b);
        }

        public AllBootsAndPlateArmorSpecification() {
        }

        @Override
        public void instructions() {
            equal("type", Optional.of(BOOTS_TYPE))
                    .or()
                    .equal("name", Optional.of(PLATE_ARMOR));
        }

    }
}
