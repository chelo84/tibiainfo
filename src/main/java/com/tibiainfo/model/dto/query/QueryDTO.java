package com.tibiainfo.model.dto.query;

import io.swagger.annotations.ApiParam;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class QueryDTO {

    @ApiParam(example = "0", allowableValues = "range[0, infinity]", required = true)
    @Min(0) @NotNull
    Integer page;

    @ApiParam(example = "1", allowableValues = "range[1, infinity]", required = true)
    @Min(1) @NotNull
    Integer size;

}
