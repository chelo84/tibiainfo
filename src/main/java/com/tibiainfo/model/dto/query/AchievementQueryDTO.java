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
public class AchievementQueryDTO extends QueryDTO {

    @Builder.Default
    @ApiParam(example = "Annihilator")
    Optional<String> name = Optional.empty();

}
