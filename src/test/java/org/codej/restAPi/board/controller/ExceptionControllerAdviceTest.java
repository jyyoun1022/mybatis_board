package org.codej.restAPi.board.controller;

import org.codej.restAPi.board.controller.exception.ExceptionController;
import org.codej.restAPi.board.exception.GlobalExceptionHandlers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ExceptionControllerAdviceTest {

    @InjectMocks
    ExceptionController exceptionController;

    MockMvc mockMvc;

    @BeforeEach
    void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(exceptionController).setControllerAdvice(new GlobalExceptionHandlers()).build();
    }
    @Test
    void entryPointTest()throws Exception{
        mockMvc.perform(
                get("/exception/entry-point"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.code").value(401));
    }
    @Test
    void accessDeniedTest() throws Exception {
        mockMvc.perform(
                        get("/exception/access-denied"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.code").value(403));
    }
}
