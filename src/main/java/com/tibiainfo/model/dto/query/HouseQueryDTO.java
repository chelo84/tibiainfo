package com.tibiainfo.model.dto.query;

import io.swagger.annotations.ApiParam;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseQueryDTO extends QueryDTO {

    @Builder.Default
    @ApiParam(example = "Warriors' Guildhall")
    Optional<String> name = Optional.empty();

    @Builder.Default
    @ApiParam(example = "Thais")
    Optional<String> city = Optional.empty();

    @Builder.Default
    @ApiParam(example = "Temple Street")
    Optional<String> street = Optional.empty();

}
