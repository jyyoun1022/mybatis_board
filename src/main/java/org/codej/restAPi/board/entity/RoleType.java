package org.codej.restAPi.board.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum RoleType {
    USER(1,"ROLE_USER","일반 사용자"),
    ADMIN(2,"ROLE_ADMIN","관리자"),
    NORMAL(3,"ROLE_NORMAL","보통 사용자"),
    UNKNOWN(4,"UNKNOWN","잘못된 사용자");

    private final int key;
    private final String value;
    private final String describe;

    public static RoleType valueOfLabel(String value){
        return Arrays.stream(RoleType.values()).filter(v -> v.equals(value)).findAny().orElse(UNKNOWN);
    }
}
