package org.codej.restAPi.board.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberJoinDto {
    private String nickname;
    private String password;
    private String name;
    private String email;
    private String birth;
    private int age;
    private int returnValue;

}
