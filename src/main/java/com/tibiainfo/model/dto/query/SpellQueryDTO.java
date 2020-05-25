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
public class SpellQueryDTO extends QueryDTO {

    @Builder.Default
    @ApiParam(example = "Find Person")
    Optional<String> name = Optional.empty();

    @Builder.Default
    @ApiParam(example = "Exura")
    Optional<String> words = Optional.empty();

    @Builder.Default
    @ApiParam(example = "Instant")
    Optional<String> type = Optional.empty();
}
