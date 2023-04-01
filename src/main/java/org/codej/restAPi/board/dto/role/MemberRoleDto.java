package org.codej.restAPi.board.dto.role;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberRoleDto {

    private Long memberRoleId;
    private Long memberId;
    private Long roleId;

}
