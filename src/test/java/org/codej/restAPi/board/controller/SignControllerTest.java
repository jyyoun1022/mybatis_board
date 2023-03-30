package org.codej.restAPi.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.codej.restAPi.board.dto.sign.SignInRequest;
import org.codej.restAPi.board.dto.sign.SignInResponse;
import org.codej.restAPi.board.dto.sign.SignUpRequest;
import org.codej.restAPi.board.service.sign.SignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SignControllerTest {

    @InjectMocks
    SignController signController;

    @Mock
    SignService signService;
    MockMvc mockMvc;
    ObjectMapper ob = new ObjectMapper();

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(signController).build();
    }

    @Test
    void signUpTest() throws Exception {
        //given
        SignUpRequest req = SignUpRequest.builder()
                .email("jyyoun1022@naver.com")
                .password("yjy^^84912891")
                .birth("1996-10-22")
                .name("윤재열")
                .nickname("CODE-J")
                .build();
        //when,then
        mockMvc.perform(
                post("/api/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ob.writeValueAsString(req)))
                .andExpect(status().isCreated());

        verify(signService).signUp(req);
    }

    @Test
    void signInTest() throws Exception {
        //given
        SignInRequest req = new SignInRequest("jyyoun1022@naver.com", "yjy^^94912891");
        given(signService.signIn(req)).willReturn(new SignInResponse("access","refresh"));
        //when,then
        mockMvc.perform(
                post("/api/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ob.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.data.accessToken").value("access"))
                .andExpect(jsonPath("$.result.data.refreshToken").value("refresh"));

        verify(signService).signIn(req);
    }

    @Test
    void ignoreNullValueInJsonResponseTest() throws Exception {
        //given
        SignUpRequest req = SignUpRequest.builder()
                .email("jyyoun1022@naver.com")
                .password("yjy^^84912891")
                .birth("1996-10-22")
                .name("윤재열")
                .nickname("CODE-J")
                .build();
        //when,then
        mockMvc.perform(
                post("/api/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ob.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result").doesNotExist());
    }
}
