package org.codej.restAPi.board.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String birth;
    private int age;
    private int returnValue;
}
