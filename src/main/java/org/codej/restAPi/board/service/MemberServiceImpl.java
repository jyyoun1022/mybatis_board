package org.codej.restAPi.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codej.restAPi.board.dto.Member;
import org.codej.restAPi.board.dto.MemberUpdateDto;
import org.codej.restAPi.board.exception.CustomException;
import org.codej.restAPi.board.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.codej.restAPi.board.exception.ErrorCode.*;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public Member read(Long id) {
        Optional<Member> result = memberMapper.findById(id);
        if(result.isPresent()){
            return result.get();
        } else {
            throw new CustomException(NOT_FOUND_MEMBER);
        }
    }

    @Transactional
    @Override
    public void withdraw(Long id) {
        if(notExistsMember(id)) throw new CustomException(NOT_FOUND_MEMBER);
        memberMapper.withdrawMember(id);
    }

    @Override
    @Transactional
    public void updateNickname(MemberUpdateDto dto) {
        String prevNickname = memberMapper.findByMemberId(dto.getMemberId());
        if (!prevNickname.equals(dto.getNickname())) {
            nicknameDuplicateCheck(dto.getNickname());
            memberMapper.updateNickname(dto);
        } else {
            throw new CustomException(NOT_CHANGE_NICKNAME);
        }
    }

    private boolean notExistsMember(Long id){
        return !memberMapper.existsByMemberId(id);
    }

    private void nicknameDuplicateCheck(String nickname)  {
        int count = memberMapper.countByNickname(nickname);
        if(count >= 1){
            throw new CustomException(DUPLICATE_EMAIL);
        }
    }


}

