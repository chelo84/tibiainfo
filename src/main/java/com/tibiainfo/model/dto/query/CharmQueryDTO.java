package com.tibiainfo.model.dto.query;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharmQueryDTO extends QueryDTO {

    @ApiParam(example = "Freeze")
    Optional<String> name = Optional.empty();
}
