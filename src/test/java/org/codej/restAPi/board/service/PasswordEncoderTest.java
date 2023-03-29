package org.codej.restAPi.board.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTest {
    @Autowired
    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();;

    @Test
    void encodeWithBcryptTest(){
        String password ="password";

        String encodedPassword = passwordEncoder.encode(password);
        System.out.println(encodedPassword);
        assertThat(encodedPassword).contains("bcrypt");
    }
    @Test
    void matchTest(){
        String password= "password";
        String encodedPassword = passwordEncoder.encode(password);
        boolean isMatch = passwordEncoder.matches(password, encodedPassword);
        assertThat(isMatch).isTrue();
    }
}
