package com.tibiainfo.model.dto.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiParam;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
