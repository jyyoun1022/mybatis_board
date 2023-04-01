package org.codej.restAPi.board.dto.member;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.codej.restAPi.board.entity.Role;

import java.time.LocalDateTime;
import java.util.Set;

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
    private Set<Role> role;
    private String birth;
    private Integer age;
    private boolean withdrawState;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private LocalDateTime withDate;

    public Member(Long memberId, String nickname, String password, String name, String email, Set<Role> role, String birth, Integer age) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.birth = birth;
        this.age = age;
    }
}
