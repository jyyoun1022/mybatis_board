package org.codej.restAPi.board.service.sign;

import org.codej.restAPi.board.security.JwtHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * TokenService를 테스트하기 위함이지 의존관계에 있는 다른 객체들을 테스트하려는것은 아니다.
 * @InjectMocks는 의존성을 가지고 있는 객체들을 가짜로 만들어서 주입받을 수 있다.
 * @Mock 객체들을 가짜로 만들어서 @InjectMocks로 지정된 객체에 주입해준다.
 * given() 메서드는 의존하는 가짜 행위가 반환해야할 데이터를 미리 준비하여 주입
 * verify() 메서드는 가짜 객체가 수행한 행위도 검증이 가능
 */
@ExtendWith(MockitoExtension.class)
public class ToKenServiceTest {
    @InjectMocks
    TokenService tokenService;
    @Mock
    JwtHandler jwtHandler;

    /**
     * @Value를 통해서 설정 파일에서 값을 읽어와야 한다.
     * 하지만 해당값을 읽어올 수 없기에 ReflectionTestUtils를 사용하여(리플렉션) 어떠한 객체의 필드값을 주입가능
     */
    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(tokenService, "accessTokenMaxAgeSeconds", 10L);
        ReflectionTestUtils.setField(tokenService, "refreshTokenMaxAgeSeconds", 10L);
        ReflectionTestUtils.setField(tokenService, "accessKey", "accessKey");
        ReflectionTestUtils.setField(tokenService, "refreshKey", "refreshKey");
    }

    /**
     *
     */
    @Test
    void createAccessTokenTest() {
        //given
        given(jwtHandler.createToken(anyString(), anyString(), anyLong())).willReturn("access");
        //when
        String token = tokenService.createAccessToken("subject");

        //then
        assertThat(token).isEqualTo("access");
        verify(jwtHandler).createToken(anyString(), anyString(), anyLong());

    }

    @Test
    void createRefreshTokenTest() {
        // given
        given(jwtHandler.createToken(anyString(), anyString(), anyLong())).willReturn("refresh");

        // when
        String token = tokenService.createRefreshToken("subject");

        // then
        assertThat(token).isEqualTo("refresh");
        verify(jwtHandler).createToken(anyString(), anyString(), anyLong());
    }

    @Test
    void validateAccessTokenTest() {
        // given
        given(jwtHandler.validate(anyString(), anyString())).willReturn(true);

        // when, then
        assertThat(tokenService.validateAccessToken("token")).isTrue();
    }

    @Test
    void invalidateAccessTokenTest() {
        // given
        given(jwtHandler.validate(anyString(), anyString())).willReturn(false);

        // when, then
        assertThat(tokenService.validateAccessToken("token")).isFalse();
    }

    @Test
    void validateRefreshTokenTest() {
        // given
        given(jwtHandler.validate(anyString(), anyString())).willReturn(true);

        // when, then
        assertThat(tokenService.validateRefreshToken("token")).isTrue();
    }

    @Test
    void invalidateRefreshTokenTest() {
        // given
        given(jwtHandler.validate(anyString(), anyString())).willReturn(false);

        // when, then
        assertThat(tokenService.validateRefreshToken("token")).isFalse();
    }

    @Test
    void extractAccessTokenSubjectTest() {
        // given
        String subject = "subject";
        given(jwtHandler.extractSubject(anyString(), anyString())).willReturn(subject);

        // when
        String result = tokenService.extractAccessTokenSubject("token");

        // then
        assertThat(subject).isEqualTo(result);
    }

    @Test
    void extractRefreshTokenSubjectTest() {
        // given
        String subject = "subject";
        given(jwtHandler.extractSubject(anyString(), anyString())).willReturn(subject);

        // when
        String result = tokenService.extractRefreshTokenSubject("token");

        // then
        assertThat(subject).isEqualTo(result);
    }
}
