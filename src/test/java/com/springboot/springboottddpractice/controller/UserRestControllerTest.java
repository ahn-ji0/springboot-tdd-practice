package com.springboot.springboottddpractice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.springboottddpractice.domain.dto.UserAddResponse;
import com.springboot.springboottddpractice.domain.dto.UserGetResponse;
import com.springboot.springboottddpractice.domain.dto.UserRequest;
import com.springboot.springboottddpractice.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("입력하신 id로 조회가 잘 되는지")
    void findById() throws Exception {
        given(userService.get(1l)).willReturn(new UserGetResponse(1l,"ppang"));

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1))
                .andDo(print());

    }

    @Test
    @DisplayName("동일한 이름으로 입력했을 때 실패 message 잘 나오는지")
    void add() throws Exception {
        UserRequest userRequest = new UserRequest("ppang","123456");
        //mockbean은 실제 빈 객체가 아니라 가짜 객체 생성해서 주입. 따라서 실제 행위를 수행하지 않는다.
        //그래서 given을 통해 동작을 정의한다.

        //userService 객체의 어떤 메소드가 호출되고 어떤 파라미터를 주입받는지 가정한 후
        //willReturn을 통해 어떤 결과를 리턴할 것인지 정의하는 구조
        given(userService.add(any())).willReturn(new UserAddResponse("ppang","해당 이름은 중복입니다!"));

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userRequest))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").exists())
                .andExpect(jsonPath("$.message").value("해당 이름은 중복입니다!"))
                //요청과 응답 내용 전체 확인
                .andDo(print());
    }

}