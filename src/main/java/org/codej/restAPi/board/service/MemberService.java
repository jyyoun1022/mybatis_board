package org.codej.restAPi.board.service;


import org.codej.restAPi.board.dto.Member;
import org.codej.restAPi.board.dto.MemberUpdateDto;

public interface MemberService {
    Member read (Long memberId);
    void updateNickname(MemberUpdateDto dto);
    void withdraw(Long memberId);
}
