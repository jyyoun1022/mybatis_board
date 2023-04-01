package org.codej.restAPi.board.entity;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Role {

    private Long roleId;
    private String roleType;


}
