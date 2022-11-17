package com.springboot.springboottddpractice.domain.dto;

import com.springboot.springboottddpractice.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class UserRequest {
    String username;
    String password;

    public User toEntity(){
        return new User(this.username,this.password);
    }
}
