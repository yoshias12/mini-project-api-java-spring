package com.enigmacamp.loanApp.model.dto.response;

import com.enigmacamp.loanApp.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String email;
    private List<Role> role;
}
