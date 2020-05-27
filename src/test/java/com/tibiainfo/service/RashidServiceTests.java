package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.query.RashidQueryDTO;
import com.tibiainfo.model.dto.rashid.RashidDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RashidServiceTests {

    private final Long EXISTING_DAY = 3L;
    private final Long NON_EXISTING_DAY = -1L;

    private final String CITY = "Svargrond";

    private final RashidQueryDTO.RashidQueryDTOBuilder<?, ?> QUERY_DTO_BUILDER;
    @Autowired
    private RashidService rashidService;

    {
        QUERY_DTO_BUILDER = RashidQueryDTO.builder()
                .page(0)
                .size(10);
    }

    @Test
    public void testGetRashid() {
        PageSupportDTO<RashidDTO> rashids = rashidService.getRashid(QUERY_DTO_BUILDER.build());

        assertNotNull(rashids);
        assertNotNull(rashids.getContent());
        assertNotNull(rashids.getNumberOfElements());
        assertNotNull(rashids.getTotalElements());
        assertNotNull(rashids.getTotalPages());

        assertFalse(rashids.isEmpty());
        assertFalse(rashids.getContent().isEmpty());
    }

    @Test
    public void testGetCity() {
        PageSupportDTO<RashidDTO> citys = rashidService.getRashid(
                QUERY_DTO_BUILDER.city(Optional.of(CITY)).build()
        );

        assertNotNull(citys);
        assertNotNull(citys.getContent());
        assertFalse(citys.isEmpty());
        assertTrue(
                citys.getContent()
                        .stream()
                        .allMatch(city -> city.getCity().equalsIgnoreCase(CITY))
        );
    }

    @Test
    public void testGetRashidByDay() throws NotFoundException {
        RashidDTO fetchedDay = rashidService.getRashidByDay(EXISTING_DAY);

        assertNotNull(fetchedDay);
        assertEquals(EXISTING_DAY, fetchedDay.getDay());
    }

    @Test(expected = NotFoundException.class)
    public void testGetDayThatDoesNotExist() throws NotFoundException {
        rashidService.getRashidByDay(NON_EXISTING_DAY);
    }

    @Test
    public void testGetItemByCity() {
        rashidService.getRashid(
                QUERY_DTO_BUILDER.city(Optional.of(CITY))
                        .city(Optional.of("Svar"))
                        .build()
        );
    }

    @Test
    public void testGetRashidWithNonExtendedJson() {
        PageSupportDTO<RashidDTO> rashids = rashidService.getRashid(
                QUERY_DTO_BUILDER.build()
        );

        assertNotNull(rashids);
        assertNotNull(rashids.getContent());
        assertFalse(rashids.isEmpty());
        assertTrue(
                rashids.getContent()
                        .stream()
                        .map(RashidDTO::getX)
                        .allMatch(Objects::isNull)
        );
    }

    @Test
    public void testGetRashidWithExtendedJson() {
        PageSupportDTO<RashidDTO> items = rashidService.getRashid(
                QUERY_DTO_BUILDER.extended(true).build()
        );

        assertNotNull(items);
        assertNotNull(items.getContent());
        assertFalse(items.isEmpty());
        assertTrue(
                items.getContent()
                        .stream()
                        .map(RashidDTO::getDay)
                        .anyMatch(Objects::nonNull)
        );
    }


}
