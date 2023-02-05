package com.api.djobs.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum Authority {

    ROLE_USER(1, "USER"),
    ROLE_ADMIN(2, "ADMIN");


    private int code;
    private String symbol;


}
