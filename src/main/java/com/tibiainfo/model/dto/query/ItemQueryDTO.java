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
public class ItemQueryDTO extends QueryDTO {

    @Builder.Default
    @ApiParam(example = "Sword Weapons")
    Optional<String> type = Optional.empty();

    @Builder.Default
    @ApiParam(example = "Fire Sword")
    Optional<String> name = Optional.empty();

}
