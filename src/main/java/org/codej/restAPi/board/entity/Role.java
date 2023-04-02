package org.codej.restAPi.board.entity;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class Role {

    private int roleId;
    private String roleType;

    public Role(String roleType) {
        this.roleType = roleType;
    }
}
