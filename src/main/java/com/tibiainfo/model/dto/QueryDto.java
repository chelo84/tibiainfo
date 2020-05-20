package com.tibiainfo.model.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class QueryDto {

    @ApiParam(example = "0", allowableValues = "range[0, infinity]", required = true)
    @Min(0) @NotNull
    Integer page;

    @ApiParam(example = "1", allowableValues = "range[1, infinity]", required = true)
    @Min(1) @NotNull
    Integer size;

}
