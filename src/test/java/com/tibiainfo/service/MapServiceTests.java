package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.map.MapDTO;
import com.tibiainfo.model.dto.query.MapQueryDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MapServiceTests {

    private final Long EXISTING_MAP = 3L;
    private final Long NON_EXISTING_MAP = -1L;

    private final MapQueryDTO.MapQueryDTOBuilder<?, ?> MAP_QUERY_DTO_BUILDER;

    @Autowired
    private MapService mapService;

    {
        MAP_QUERY_DTO_BUILDER = MapQueryDTO.builder()
                .page(0)
                .size(10);
    }

    @Test
    public void testGetMaps() {
        PageSupportDTO<MapDTO> maps = mapService.getMaps(MAP_QUERY_DTO_BUILDER.build());

        assertNotNull(maps);
        assertNotNull(maps.getContent());
        assertNotNull(maps.getNumberOfElements());
        assertNotNull(maps.getTotalElements());
        assertNotNull(maps.getTotalPages());

        assertFalse(maps.isEmpty());
        assertFalse(maps.getContent().isEmpty());
        assertFalse(maps.isLast());
    }

    @Test
    public void testGetMapByZ() throws NotFoundException {
        MapDTO fetchedZ = mapService.getMapByZ(EXISTING_MAP);

        assertNotNull(fetchedZ);
        assertEquals(EXISTING_MAP, fetchedZ.getZ());
    }

    @Test(expected = NotFoundException.class)
    public void testGetMapThatDoesNotExist() throws NotFoundException {
        mapService.getMapByZ(NON_EXISTING_MAP);
    }

    @Test
    public void testGetMapImage() throws NotFoundException {
        byte[] image = mapService.getImage(EXISTING_MAP);

        assertNotNull(image);
    }

    @Test(expected = NotFoundException.class)
    public void testGetImageForMapThatDoesNotExist() throws NotFoundException {
        mapService.getImage(NON_EXISTING_MAP);
    }


}
