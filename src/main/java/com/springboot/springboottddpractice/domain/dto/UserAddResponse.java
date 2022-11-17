package com.springboot.springboottddpractice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class UserAddResponse {
    private String username;
    private String message;
}
