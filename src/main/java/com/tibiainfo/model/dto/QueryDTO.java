package com.tibiainfo.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryDTO {

    @ApiParam(example = "0", allowableValues = "range[0, infinity]", required = true)
    @Min(0) @NotNull
    Integer page;

    @ApiParam(example = "1", allowableValues = "range[1, infinity]", required = true)
    @Min(1) @NotNull
    Integer size;

}
