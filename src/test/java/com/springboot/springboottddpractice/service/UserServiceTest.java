package com.springboot.springboottddpractice.service;

import com.springboot.springboottddpractice.domain.dto.UserAddResponse;
import com.springboot.springboottddpractice.domain.dto.UserRequest;
import com.springboot.springboottddpractice.domain.entity.User;
import com.springboot.springboottddpractice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {
    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private UserService userService;

    @BeforeEach
    void setUp(){
        userService = new UserService(userRepository);
    }

    @Test
    void addTest(){
        Mockito.when(userRepository.save(any()))
                .thenReturn(new User(1l,"ppang","1234"));

        UserAddResponse userAddResponse = userService.add(new UserRequest("ppang","1234"));
        assertEquals("ppang",userAddResponse.getUsername());
        assertEquals("가입이 완료되었습니다.",userAddResponse.getMessage());


    }

}