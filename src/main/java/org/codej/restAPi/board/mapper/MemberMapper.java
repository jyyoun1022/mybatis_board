package org.codej.restAPi.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.codej.restAPi.board.dto.member.Member;
import org.codej.restAPi.board.dto.member.MemberUpdateDto;
import org.codej.restAPi.board.dto.sign.SignUpRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Mapper
@Component
public interface MemberMapper {

    int save(SignUpRequest req);
    int countByNickname(String nickname);
    void withdrawMember(Long memberId);
    String findByMemberId(int memberId);
    int updateNickname(MemberUpdateDto dto);

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    boolean existsByMemberId(Long id);

    String getPassword(String email);
    int getMemberId(String email);

    Optional<Member> findByEmail(String email);
    Optional<Member> findById(Long id);
}
