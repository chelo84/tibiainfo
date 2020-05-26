package com.tibiainfo.model.dto.query;

import io.swagger.annotations.ApiParam;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OutfitQueryDTO extends QueryDTO {

    @Builder.Default
    @ApiParam(example = "Retro Knight")
    Optional<String> name = Optional.empty();

}
