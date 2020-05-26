package com.tibiainfo.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OutfitSex {

    MALE("Male"),
    FEMALE("Female");

    private String sex;

}