package com.tibiainfo.model.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class CreatureQueryDto extends QueryDto {

    @ApiParam(example = "Monk")
    Optional<String> name = Optional.empty();
}
