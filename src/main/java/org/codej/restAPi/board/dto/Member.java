package org.codej.restAPi.board.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
@Alias("Member")
public class Member {

    private Long memberId;
    private String nickname;
    private String password;
    private String name;
    private String email;
    private String role;
    private String birth;
    private Integer age;
    private boolean withdrawState;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private LocalDateTime withDate;

    public Member(String nickname, String password, String name, String email, String role, String birth, Integer age) {
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.birth = birth;
        this.age = age;
    }
}
