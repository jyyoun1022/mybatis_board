package org.codej.restAPi.board.service;

import org.codej.restAPi.board.dto.MemberJoinDto;
import org.codej.restAPi.board.dto.MemberUpdateDto;
import org.codej.restAPi.board.exception.CustomException;
import org.codej.restAPi.board.mapper.MemberMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberMapper mapper;

    @Test
    void joinMember() throws CustomException {
        MemberJoinDto dto = MemberJoinDto.builder()
                .name("codej")
                .email("jyyoun1021@gmail.com")
                .nickname("codej")
                .password("yjy^^46232891")
                .birth("1996-10-22")
                .build();
        memberService.joinMember(dto);
        int result = dto.getReturnValue();
        System.out.println("리턴값 ::: "+result);
    }
    @Test
    void 오류_회원가입시_이름이_없음(){
        MemberJoinDto dto = MemberJoinDto.builder()
                .email("jyyoun1021@gmail.com")
                .nickname("회원가입1")
                .password("yjy^^46232891")
                .birth("1996-10-22")
                .build();

        Assertions.assertThrows(CustomException.class, () -> memberService.joinMember(dto));
    }
    @Test
    void updateMember(){
        MemberUpdateDto dto = MemberUpdateDto.builder()
                .memberId(1)
                .nickname("codej")
                .build();
        memberService.updateNickname(dto);
    }
    @Test
    void withdrawMember(){
        memberService.withdrawMember(1);
    }
    @Test
    void findByMemberId(){
        String byMemberId = mapper.findByMemberId(1);
        System.out.println(byMemberId);
    }
    @Test
    void test(){
        boolean flag = mapper.existsByEmail("jyyoun1021@gmail.com");
        System.out.println(flag);
        boolean codej2 = mapper.existsByNickname("codej2");
        System.out.println(codej2);
        boolean codej1 = mapper.existsByNickname("codej1");
        System.out.println(codej1);
    }
}