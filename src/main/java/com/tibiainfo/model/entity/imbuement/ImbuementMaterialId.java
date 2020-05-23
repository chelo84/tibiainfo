package com.tibiainfo.model.entity.imbuement;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ImbuementMaterialId implements Serializable {

    Long imbuementId;

    Long item;
}
