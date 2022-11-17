package com.springboot.springboottddpractice.controller;

import com.springboot.springboottddpractice.domain.dto.UserGetResponse;
import com.springboot.springboottddpractice.domain.dto.UserRequest;
import com.springboot.springboottddpractice.domain.dto.UserAddResponse;
import com.springboot.springboottddpractice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetResponse> get(@PathVariable Long id){
        UserGetResponse userGetResponse = userService.get(id);
        return ResponseEntity.ok().body(userGetResponse);
    }

    @PostMapping
    public ResponseEntity<UserAddResponse> add(@RequestBody UserRequest userRequest){
        UserAddResponse userAddResponse = userService.add(userRequest);
        return ResponseEntity.ok().body(userAddResponse);
    }
}
