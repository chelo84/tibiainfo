package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.charm.CharmDTO;
import com.tibiainfo.model.dto.query.CharmQueryDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CharmServiceTests {

    private final Long EXISTING_CHARM = 31700l;
    private final Long NON_EXISTING_CHARM = -1L;

    private final String OFFENSIVE_TYPE = "Offensive";

    private final CharmQueryDTO.CharmQueryDTOBuilder<?, ?> CHARM_QUERY_DTO_BUILDER;
    @Autowired
    private CharmService charmService;

    {
        CHARM_QUERY_DTO_BUILDER = CharmQueryDTO.builder()
                .page(0)
                .size(10);
    }

    @Test
    public void testGetCharm() {
        PageSupportDTO<CharmDTO> charms = charmService.getCharms(CHARM_QUERY_DTO_BUILDER.build());

        assertNotNull(charms);
        assertNotNull(charms.getContent());
        assertNotNull(charms.getNumberOfElements());
        assertNotNull(charms.getTotalElements());
        assertNotNull(charms.getTotalPages());

        assertFalse(charms.isEmpty());
        assertFalse(charms.getContent().isEmpty());
        assertFalse(charms.isLast());
    }

    @Test
    public void testGetCharmOfType() {
        PageSupportDTO<CharmDTO> charms = charmService.getCharms(
                CHARM_QUERY_DTO_BUILDER.type(Optional.of(OFFENSIVE_TYPE)).extended(true).build()
        );

        assertNotNull(charms);
        assertNotNull(charms.getContent());
        assertFalse(charms.isEmpty());
        assertTrue(
                charms.getContent()
                        .stream()
                        .allMatch(charm -> charm.getType().equalsIgnoreCase(OFFENSIVE_TYPE))
        );
    }

    @Test
    public void testGetCharmById() throws NotFoundException {
        CharmDTO fetchedCharm = charmService.getCharmById(EXISTING_CHARM);

        assertNotNull(fetchedCharm);
        assertEquals(EXISTING_CHARM, fetchedCharm.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetItemThatDoesNotExist() throws NotFoundException {
        charmService.getCharmById(NON_EXISTING_CHARM);
    }

    @Test
    public void testGetCharmImage() throws NotFoundException {
        byte[] image = charmService.getImage(EXISTING_CHARM);

        assertNotNull(image);
    }

    @Test(expected = NotFoundException.class)
    public void testGetCharmImageForAnCharmThatDoesNotExist() throws NotFoundException {
        charmService.getImage(NON_EXISTING_CHARM);
    }

    @Test
    public void testGetCharmByNameAndType() {
        charmService.getCharms(
                CHARM_QUERY_DTO_BUILDER.type(Optional.of(OFFENSIVE_TYPE))
                        .name(Optional.of("Offfencive"))
                        .build()
        );
    }

}
