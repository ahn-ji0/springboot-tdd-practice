package com.springboot.springboottddpractice.service;

import com.springboot.springboottddpractice.domain.dto.UserGetResponse;
import com.springboot.springboottddpractice.domain.dto.UserRequest;
import com.springboot.springboottddpractice.domain.dto.UserAddResponse;
import com.springboot.springboottddpractice.domain.entity.User;
import com.springboot.springboottddpractice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserGetResponse get(Long id){
        Optional<User> usersOptional = userRepository.findById(id);
        User user = usersOptional.get();
        UserGetResponse userGetResponse = UserGetResponse.builder().id(user.getId()).username(user.getUsername()).build();
        return userGetResponse;
    }

    public UserAddResponse add(UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findAllByUsername(userRequest.getUsername());
        UserAddResponse userAddResponse = null;
        if(optionalUser.isEmpty()) {
            User savedUser = userRepository.save(userRequest.toEntity());
            userAddResponse = UserAddResponse.builder().username(savedUser.getUsername()).message("가입이 완료되었습니다.").build();
        }else {
            userAddResponse = UserAddResponse.builder().username(userRequest.getUsername()).message("해당 이름은 중복입니다!").build();
        }
        return userAddResponse;
    }
}
