package org.codej.restAPi.board.service.sign;

import org.codej.restAPi.board.dto.member.Member;
import org.codej.restAPi.board.dto.sign.SignInRequest;
import org.codej.restAPi.board.dto.sign.SignInResponse;
import org.codej.restAPi.board.dto.sign.SignUpRequest;
import org.codej.restAPi.board.exception.CustomException;
import org.codej.restAPi.board.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SignServiceTest {

    @InjectMocks
    SignService signService;
    @Mock
    MemberMapper memberMapper;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    TokenService tokenService;

    @Test
    void signUpTest(){
        //given
        SignUpRequest req = createSignUpRequest();
        given(passwordEncoder.encode(any())).willReturn("password1");
        //when
        signService.signUp(req);
        //then
        verify(passwordEncoder).encode(req.getPassword());
        verify(memberMapper).save(any());
    }
    @Test
    void validateSignUpDuplicateEmailTest(){
        //given
        given(memberMapper.existsByEmail(anyString())).willReturn(true);
        //when//then
        assertThatThrownBy(() -> signService.signUp(createSignUpRequest()))
                .isInstanceOf(CustomException.class);
    }
    @Test
    void validateSignUpByDuplicateNicknameTest(){
        //given
        given(memberMapper.existsByNickname(anyString())).willReturn(true);
        //when,then
        assertThatThrownBy(() -> signService.signUp(createSignUpRequest()))
                .isInstanceOf(CustomException.class);
    }
    @Test
    void signInTest(){
        //given
//        given(memberMapper.findByEmail(any())).willReturn(Optional.of(createMember()));
        given(passwordEncoder.matches(anyString(),anyString())).willReturn(true);
        given(tokenService.createAccessToken(anyString())).willReturn("access");
        given(tokenService.createRefreshToken(anyString())).willReturn("refresh");
        given(memberMapper.getPassword(anyString())).willReturn("password");

        //when
        SignInResponse res = signService.signIn(new SignInRequest("email", "password"));
        //then
        assertThat(res.getAccessToken()).isEqualTo("access");
        assertThat(res.getRefreshToken()).isEqualTo("refresh");
    }
    @Test
    void signInExceptionByNoneMemberTest(){
        //given
        given(memberMapper.getPassword(anyString())).willReturn("password1");
        //when,then
        assertThatThrownBy(() -> signService.signIn(new SignInRequest("email","password")))
                .isInstanceOf(CustomException.class);
    }
    private SignUpRequest createSignUpRequest() {
        return new SignUpRequest("email@gmail.com", "password1", "codej", "윤재열","1996-10-22");
    }

    private Member createMember() {
        return new Member(1L,"codej", "password", "윤재열", "email@gmail.com", Set.of(), "1996-1022", 26);
    }

}
