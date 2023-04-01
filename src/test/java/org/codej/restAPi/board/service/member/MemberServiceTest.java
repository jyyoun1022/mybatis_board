package org.codej.restAPi.board.service.member;

import org.codej.restAPi.board.dto.member.Member;
import org.codej.restAPi.board.exception.CustomException;
import org.codej.restAPi.board.mapper.MemberMapper;
import org.codej.restAPi.board.service.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    MemberServiceImpl memberService;
    @Mock
    MemberMapper memberMapper;

    @Test
    void readTest(){
        //given
        Member member = createMember();
        given(memberMapper.findById(anyLong())).willReturn(Optional.of(member));
        //when
        Member result = memberService.read(anyLong());
        System.out.println(result);
        //then
        assertThat(result.getEmail()).isEqualTo(member.getEmail());
    }
    @Test
    void readExceptionByMemberNotFoundTest(){
        //given
        given(memberMapper.findById(anyLong())).willReturn(Optional.ofNullable(null));
        //when,then
        assertThatThrownBy(() -> memberService.read(anyLong())).isInstanceOf(CustomException.class);
    }

    @Test
    void withdrawTest(){
        //given
        given(memberMapper.existsByMemberId(anyLong())).willReturn(true);
        //when
        memberService.withdraw(anyLong());
        //then
        verify(memberMapper).withdrawMember(anyLong());
    }
    @Test
    void withdrawExceptionByMemberNotFoundTest() {
        //given
        given(memberMapper.existsByMemberId(anyLong())).willReturn(false);
        //when,then
        assertThatThrownBy(() -> memberService.withdraw(anyLong())).isInstanceOf(CustomException.class);
    }
    private Member createMember(){
        return Member.builder()
                .email("jyyoun1021@gmail.com")
                .password("yjy^^46232891")
                .name("윤재열")
                .nickname("CODEJ")
                .birth("1996-10-22")
                .build();
    }


}
