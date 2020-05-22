package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.house.HouseDTO;
import com.tibiainfo.model.dto.query.HouseQueryDTO;
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
public class HouseServiceTest {

    private final Long EXISTING_HOUSE = 7356L;
    private final Long NON_EXISTING_HOUSE = -1L;

    private final String HOUSE_NAME = "Warriors' Guildhall";
    private final String HOUSE_CITY = "Thais";
    private final String HOUSE_STREET = "Temple Street";

    private final HouseQueryDTO.HouseQueryDTOBuilder<?, ?> QUERY_DTO_BUILDER;

    {
        QUERY_DTO_BUILDER = HouseQueryDTO.builder()
                .page(0)
                .size(10);
    }

    @Autowired
    private HouseService houseService;

    @Test
    public void testGetHouses() {
        PageSupportDTO<HouseDTO> houses = houseService.getHouses(QUERY_DTO_BUILDER.build());

        assertNotNull(houses);
        assertNotNull(houses.getContent());
        assertNotNull(houses.getNumberOfElements());
        assertNotNull(houses.getTotalElements());
        assertNotNull(houses.getTotalPages());

        assertFalse(houses.isEmpty());
        assertFalse(houses.getContent().isEmpty());
        assertFalse(houses.isLast());
    }

    @Test
    public void testGetHousesOfName() {
        PageSupportDTO<HouseDTO> houses = houseService.getHouses(
                QUERY_DTO_BUILDER.name(Optional.of(HOUSE_NAME)).build()
        );

        assertNotNull(houses);
        assertNotNull(houses.getContent());
        assertFalse(houses.isEmpty());
        assertTrue(
                houses.getContent()
                        .stream()
                        .allMatch(house -> house.getName().equalsIgnoreCase(HOUSE_NAME))
        );
    }

    @Test
    public void testGetHousesFromCity() {
        PageSupportDTO<HouseDTO> houses = houseService.getHouses(
                QUERY_DTO_BUILDER.city(Optional.of(HOUSE_CITY)).build()
        );

        assertNotNull(houses);
        assertNotNull(houses.getContent());
        assertFalse(houses.isEmpty());
        assertTrue(
                houses.getContent()
                        .stream()
                        .allMatch(house -> house.getCity().equalsIgnoreCase(HOUSE_CITY))
        );
    }

    @Test
    public void testGetHousesOfNameFromCity() {
        PageSupportDTO<HouseDTO> houses = houseService.getHouses(
                QUERY_DTO_BUILDER.name(Optional.of(HOUSE_NAME))
                        .city(Optional.of(HOUSE_CITY)).build()
        );

        assertNotNull(houses);
        assertNotNull(houses.getContent());
        assertFalse(houses.isEmpty());
        assertTrue(
                houses.getContent()
                        .stream()
                        .allMatch(house -> house.getCity().equalsIgnoreCase(HOUSE_CITY) && house.getName().equalsIgnoreCase(HOUSE_NAME))
        );
    }

    @Test
    public void testGetHousesFromStreet() {
        PageSupportDTO<HouseDTO> houses = houseService.getHouses(
                QUERY_DTO_BUILDER.street(Optional.of(HOUSE_STREET)).build()
        );

        assertNotNull(houses);
        assertNotNull(houses.getContent());
        assertFalse(houses.isEmpty());
        assertTrue(
                houses.getContent()
                        .stream()
                        .allMatch(house -> house.getStreet().equalsIgnoreCase(HOUSE_STREET))
        );
    }

    @Test
    public void testGetHouseById() throws NotFoundException {
        HouseDTO fetchedHouse = houseService.getHouseById(EXISTING_HOUSE);

        assertNotNull(fetchedHouse);
        assertEquals(EXISTING_HOUSE, fetchedHouse.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetHouseThatDoesNotExist() throws NotFoundException {
        houseService.getHouseById(NON_EXISTING_HOUSE);
    }

}
