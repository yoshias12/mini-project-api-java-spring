package com.enigmacamp.loanApp.model.dto.response;

import com.enigmacamp.loanApp.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private String email;
    private Role.ERole role;
}
