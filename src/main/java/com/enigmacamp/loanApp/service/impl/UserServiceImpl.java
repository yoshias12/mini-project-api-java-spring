package com.enigmacamp.loanApp.service.impl;

import com.enigmacamp.loanApp.model.dto.response.UserResponse;
import com.enigmacamp.loanApp.model.entity.AppUser;
import com.enigmacamp.loanApp.model.entity.Customer;
import com.enigmacamp.loanApp.repository.AppUserRepository;
import com.enigmacamp.loanApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AppUserRepository userRepository;

    @Override
    public AppUser loadUserById(String Id) {
        AppUser user = userRepository.findById(Id).orElseThrow(() -> new UsernameNotFoundException("Invalid credentials user"));
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Invalid credentials user"));
        return AppUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .build();
    }

    @Override
    public UserResponse getUserById(String Id) {
        AppUser user = findByidOrThrowNotFound(Id);
        return convertToUserResponse(user);
    }

    private UserResponse convertToUserResponse(AppUser user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .role(user.getRoles())
                .build();
    }

    private AppUser findByidOrThrowNotFound(String id){
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Data not found"));
    }
}
