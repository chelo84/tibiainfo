package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.house.HouseDTO;
import com.tibiainfo.model.dto.query.HouseQueryDTO;
import com.tibiainfo.service.HouseService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/houses")
@Api(tags = {"House"})
@SwaggerDefinition(tags = {
        @Tag(name = "House", description = "House Resources")
})
public class HouseController {

    @Autowired
    HouseService houseService;

    @GetMapping
    @ApiOperation(value = "Returns a page of houses")
    public PageSupportDTO<HouseDTO> getHouses(HouseQueryDTO houseQueryDTO) {
        return houseService.getHouses(houseQueryDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns a specific house")
    public HouseDTO getHouseById(@ApiParam(example = "7356") @PathVariable Long id) throws NotFoundException {
        return houseService.getHouseById(id);
    }

}
