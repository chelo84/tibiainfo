package com.tibiainfo.controller;

import com.tibiainfo.exception.NotFoundException;
import com.tibiainfo.model.dto.PageSupportDTO;
import com.tibiainfo.model.dto.query.RashidQueryDTO;
import com.tibiainfo.model.dto.rashid.RashidDTO;
import com.tibiainfo.service.RashidService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/rashid")
@SwaggerDefinition(tags = {
        @Tag(name = "Rashid Position", description = "Rashid Position Resources")
})
@Api(tags = "Rashid Position")
public class RashidController {

    @Autowired
    RashidService rashidService;

    @GetMapping
    @ApiOperation(value = "Returns a page of rashid")
    public PageSupportDTO<RashidDTO> getRashid(@Valid RashidQueryDTO queryDTO) {
        return rashidService.getRashid(queryDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Return a specific rashid position at each day")
    public RashidDTO getRashidByDay(@PathVariable Long day) throws NotFoundException {
        return rashidService.getRashidByDay(day);
    }

}
