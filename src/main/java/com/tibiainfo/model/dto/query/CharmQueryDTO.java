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
public class CharmQueryDTO extends QueryDTO {

    @Builder.Default
    @ApiParam(example = "Freeze")
    Optional<String> name = Optional.empty();

    @Builder.Default
    @ApiParam(example = "Defensive")
    Optional<String> type = Optional.empty();

}
