package org.codej.restAPi.board.service;

import org.codej.restAPi.board.security.JwtHandler;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

public class JwtHandlerTest{
    JwtHandler jwtHandler = new JwtHandler();

    @Test
    void createTokenTest(){
        //given
        String encodedKey = Base64.getEncoder().encodeToString("myKey".getBytes());
        System.out.println(encodedKey);

        String token = createToken(encodedKey, "subject", 60L);

        assertThat(token).contains("Bearer ");
    }
    @Test
    void extractSubjectTest(){
        String encodedKey = Base64.getEncoder().encodeToString("myKey".getBytes());
        String subject = "subject";
        String token = createToken(encodedKey, subject, 60L);

        String extractedSubject = jwtHandler.extractSubject(encodedKey, token);
        System.out.println(extractedSubject);
        assertThat(extractedSubject).isEqualTo(subject);
    }
    @Test
    void validationTest(){
        String encodedKey = Base64.getEncoder().encodeToString("myKey".getBytes());
        String token = createToken(encodedKey, "subject", 60L);

        boolean isValid = jwtHandler.validate(encodedKey, token);
        assertThat(isValid).isTrue();

    }

    @Test
    void invalidateByInvalidKeyTest(){
        // given
        String encodedKey = Base64.getEncoder().encodeToString("myKey".getBytes());
        String token = createToken(encodedKey, "subject", 60L);

        // when
        boolean isValid = jwtHandler.validate("invalid", token);

        // then
        assertThat(isValid).isFalse();
    }

    @Test
    void invalidateByExpiredTokenTest() {
        // given
        String encodedKey = Base64.getEncoder().encodeToString("myKey".getBytes());
        String token = createToken(encodedKey, "subject", 0L);

        // when
        boolean isValid = jwtHandler.validate(encodedKey, token);

        // then
        assertThat(isValid).isFalse();
    }
    private String createToken(String encodedKey, String subject, long maxAgeSeconds) {
        return jwtHandler.createToken(
                encodedKey,
                subject,
                maxAgeSeconds);
    }
}
