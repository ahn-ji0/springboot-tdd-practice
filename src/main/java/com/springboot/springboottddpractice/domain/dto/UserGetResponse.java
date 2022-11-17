package com.springboot.springboottddpractice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserGetResponse {
    private Long id;
    private String username;
}
