package com.enigmacamp.loanApp.service;

import com.enigmacamp.loanApp.model.dto.response.UserResponse;
import com.enigmacamp.loanApp.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserById(String Id);
    UserResponse getUserById(String Id);

}
