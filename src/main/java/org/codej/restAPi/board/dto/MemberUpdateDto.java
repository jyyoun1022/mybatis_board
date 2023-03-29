package org.codej.restAPi.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberUpdateDto {

    private int memberId;
    private String nickname;
    private int returnValue;
}
