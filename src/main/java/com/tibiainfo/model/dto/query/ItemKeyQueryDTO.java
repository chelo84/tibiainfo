package com.tibiainfo.model.dto.query;

import io.swagger.annotations.ApiParam;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import java.util.Optional;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemKeyQueryDTO extends QueryDTO {

    @Builder.Default
    @ApiParam(example = "3142", allowableValues = "range[0, infinity]")
    Optional<@Min(0) Integer> number = Optional.empty();

}
