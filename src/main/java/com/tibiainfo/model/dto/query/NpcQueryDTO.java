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
public class NpcQueryDTO extends QueryDTO{

    @Builder.Default
    @ApiParam(example = "Rookgaard")
    Optional<String> city = Optional.empty();

    @Builder.Default
    @ApiParam(example = "Obi")
    Optional<String> name = Optional.empty();

}
