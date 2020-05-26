package com.tibiainfo.service;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.mount.MountDTO;
import com.tibiainfo.model.dto.query.MountQueryDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MountServiceTests {

    private final Long EXISTING_MOUNT = 50260L;
    private final Long NON_EXISTING_MOUNT = -1L;

    private final String NAME = "Draptor";

    private final MountQueryDTO.MountQueryDTOBuilder<?, ?> QUERY_DTO_BUILDER;
    @Autowired
    private MountService mountService;

    {
        QUERY_DTO_BUILDER = MountQueryDTO.builder()
                .page(0)
                .size(10);
    }

    @Test
    public void testGetMounts() {
        PageSupportDTO<MountDTO> mounts = mountService.getMounts(QUERY_DTO_BUILDER.build());

        assertNotNull(mounts);
        assertNotNull(mounts.getContent());
        assertNotNull(mounts.getNumberOfElements());
        assertNotNull(mounts.getTotalElements());
        assertNotNull(mounts.getTotalPages());

        assertFalse(mounts.isEmpty());
        assertFalse(mounts.getContent().isEmpty());
        assertFalse(mounts.isLast());
    }

    @Test
    public void testGetMountOfName() {
        PageSupportDTO<MountDTO> mounts = mountService.getMounts(
                QUERY_DTO_BUILDER.name(Optional.of(NAME)).build()
        );

        assertNotNull(mounts);
        assertNotNull(mounts.getContent());
        assertFalse(mounts.isEmpty());
        assertTrue(
                mounts.getContent()
                        .stream()
                        .allMatch(mount -> mount.getName().equalsIgnoreCase(NAME))
        );
    }

    @Test
    public void testGetMountById() throws NotFoundException {
        MountDTO fetchedMount = mountService.getMountById(EXISTING_MOUNT);

        assertNotNull(fetchedMount);
        assertEquals(EXISTING_MOUNT, fetchedMount.getId());
    }

    @Test(expected = NotFoundException.class)
    public void testGetMountThatDoesNotExist() throws NotFoundException {
        mountService.getMountById(NON_EXISTING_MOUNT);
    }

    @Test
    public void testGetMountImage() throws NotFoundException {
        byte[] image = mountService.getImage(EXISTING_MOUNT);

        assertNotNull(image);
    }

    @Test(expected = NotFoundException.class)
    public void testGetMountImageForAnMountThatDoesNotExist() throws NotFoundException {
        mountService.getImage(NON_EXISTING_MOUNT);
    }

    @Test
    public void testGetMountByName() {
        mountService.getMounts(
                QUERY_DTO_BUILDER.name(Optional.of(NAME))
                        .name(Optional.of("Draptor"))
                        .build()
        );
    }

    @Test
    public void testGetMountWithNonExtendedJson() {
        PageSupportDTO<MountDTO> mounts = mountService.getMounts(
                QUERY_DTO_BUILDER.build()
        );

        assertNotNull(mounts);
        assertNotNull(mounts.getContent());
        assertFalse(mounts.isEmpty());
        assertTrue(
                mounts.getContent()
                        .stream()
                        .map(MountDTO::getPrice)
                        .allMatch(Objects::isNull)
        );
    }

    @Test
    public void testGetMountWithExtendedJson() {
        PageSupportDTO<MountDTO> mounts = mountService.getMounts(
                QUERY_DTO_BUILDER.extended(true).build()
        );

        assertNotNull(mounts);
        assertNotNull(mounts.getContent());
        assertFalse(mounts.isEmpty());
        assertTrue(
                mounts.getContent()
                        .stream()
                        .map(MountDTO::getName)
                        .anyMatch(Objects::nonNull)
        );
    }


}
