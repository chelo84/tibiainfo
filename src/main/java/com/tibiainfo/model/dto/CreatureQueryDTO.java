package com.tibiainfo.model.dto;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatureQueryDTO extends QueryDTO {

    @ApiParam(example = "Monk")
    Optional<String> name = Optional.empty();
}
