package org.codej.restAPi.board.entity;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class MemberRole {

    private Long memberId;
    private Role role;
}
