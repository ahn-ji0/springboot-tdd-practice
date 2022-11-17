package com.springboot.springboottddpractice.domain.entity;

import com.springboot.springboottddpractice.domain.dto.UserAddResponse;
import com.springboot.springboottddpractice.domain.dto.UserGetResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
