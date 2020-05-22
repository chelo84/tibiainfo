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
public class CreatureQueryDTO extends QueryDTO {

    @Builder.Default
    @ApiParam(example = "Monk")
    Optional<String> name = Optional.empty();
}