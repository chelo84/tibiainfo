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
public class ImbuementQueryDTO extends QueryDTO {

    @Builder.Default
    @ApiParam(example = "Intricate Venom")
    Optional<String> name = Optional.empty();
}
