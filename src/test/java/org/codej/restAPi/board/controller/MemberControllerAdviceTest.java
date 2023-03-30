package org.codej.restAPi.board.controller;

import org.codej.restAPi.board.exception.CustomException;
import org.codej.restAPi.board.exception.GlobalExceptionHandlers;
import org.codej.restAPi.board.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.codej.restAPi.board.exception.ErrorCode.NOT_FOUND_MEMBER;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class MemberControllerAdviceTest {

    @InjectMocks
    MemberApiController memberApiController;
    @Mock
    MemberService memberService;
    MockMvc mockMvc;


    @BeforeEach
    void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(memberApiController).
                setControllerAdvice(new GlobalExceptionHandlers()).build();
    }

    @Test
    void readMemberNotFoundException() throws Exception {
        //given
        given(memberService.read(anyLong())).willThrow(new CustomException(NOT_FOUND_MEMBER));
        //when,then
        mockMvc.perform(
                get("/api/members/{id}",2L))
//                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value(404));
    }
    @Test
    void deleteMemberNotFoundExceptionTest() throws Exception{
        // given
//        doThrow(MemberNotFoundException.class).when(memberService).delete(anyLong());
//
//        // when, then
//        mockMvc.perform(
//                        delete("/api/members/{id}", 1L))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.code").value(-1007));
    }
}
