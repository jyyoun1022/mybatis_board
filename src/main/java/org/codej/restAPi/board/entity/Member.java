package org.codej.restAPi.board.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
public class Member {

    private Long memberId;
    private String nickname;
    private String password;
    private String name;
    private String email;
    private Role role;
    private String birth;
    private Integer age;
    private boolean withdrawState;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private LocalDateTime withDate;
}
