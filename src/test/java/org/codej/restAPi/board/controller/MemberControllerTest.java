package org.codej.restAPi.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.codej.restAPi.board.dto.MemberUpdateDto;
import org.codej.restAPi.board.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    @InjectMocks
    MemberApiController memberController;
    @Mock
    MemberService memberService;
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }
    @Test
    void readTest() throws Exception {
        //given
        Long id = 1L;
        //when,then
        mockMvc.perform(
                get("/api/members/{id}",id))
                .andExpect(status().isOk());

        verify(memberService).read(id);
    }

    @Test
    void withdrawTest() throws Exception {
        //given
        Long id = 1L;
        //when, then
        mockMvc.perform(
                delete("/api/members/{id}",id))
                .andExpect(status().isOk());

        verify(memberService).withdraw(id);
    }

    @Test
    void updateNickName() throws Exception {
        //given
        MemberUpdateDto dto = MemberUpdateDto.builder().memberId(1).nickname("changedNick").build();
        //when,then
        mockMvc.perform(
                patch("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))

                .andExpect(status().isNoContent());
        verify(memberService).updateNickname(dto);
    }
}
