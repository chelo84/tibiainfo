package com.tibiainfo.model.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class ItemQueryDto extends QueryDto {

    @ApiParam(example = "Armors")
    Optional<String> type;

}
