package com.enigmacamp.loanApp.service.impl;

import com.enigmacamp.loanApp.Security.JwtUtil;
import com.enigmacamp.loanApp.model.dto.request.AuthRequest;
import com.enigmacamp.loanApp.model.dto.request.CustomerRequest;
import com.enigmacamp.loanApp.model.dto.response.LoginResponse;
import com.enigmacamp.loanApp.model.dto.response.RegisterResponse;
import com.enigmacamp.loanApp.model.entity.*;
import com.enigmacamp.loanApp.repository.AdminRepository;
import com.enigmacamp.loanApp.repository.AppUserRepository;
import com.enigmacamp.loanApp.repository.CustomerRepository;
import com.enigmacamp.loanApp.repository.StaffRepository;
import com.enigmacamp.loanApp.service.AuthService;
import com.enigmacamp.loanApp.service.CustomerService;
import com.enigmacamp.loanApp.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AppUserRepository appUserRepository;
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final RoleService roleService;
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Value("admin")
    private String superAdminEmail;
    @Value("123")
    private String superAdminPassword;

    @Transactional
    @PostConstruct
    public void initSuperAdmin() {
        Optional<AppUser> currentUser = appUserRepository.findByEmail(superAdminEmail);
        if (currentUser.isPresent()) return;

        Role superAdmin = roleService.getOrSave(Role.ERole.ROLE_ADMIN);
        Role staff = roleService.getOrSave(Role.ERole.ROLE_STAFF);
        Role customer = roleService.getOrSave(Role.ERole.ROLE_CUSTOMER);

        AppUser appUser = AppUser.builder()
                .email(superAdminEmail)
                .password(passwordEncoder.encode(superAdminPassword))
                .roles(List.of(superAdmin, staff, customer))
                .build();
        appUserRepository.saveAndFlush(appUser);

        Admin admin = Admin.builder()
                .appUser(appUser)
                .build();
        adminRepository.saveAndFlush(admin);
    }
    @Transactional
    @Override
    public RegisterResponse registerCustomerUser(AuthRequest<CustomerRequest> request) {
        Role role = roleService.getOrSave(Role.ERole.ROLE_CUSTOMER);
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        user = appUserRepository.save(user);

//        CustomerRequest requestData = request.getData().orElseThrow(
//                () -> new IllegalArgumentException("Customer data is missing")
//        );

//        requestData.setUser(user);
//        customerRepository.saveAndFlush(convertToCustomer(requestData));

        return RegisterResponse.builder()
                .email(user.getEmail())
                .role(role.getRole())
                .build();

    }
    @Transactional
    @Override
    public RegisterResponse registerStaffUser(AuthRequest<Staff> request) {
        Role role = roleService.getOrSave(Role.ERole.ROLE_STAFF);
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        user = appUserRepository.save(user);

//        Staff requestData = request.getData().orElseThrow(
//                () -> new IllegalArgumentException("Customer data is missing")
//        );

//        requestData.setAppUser(user);
//        staffRepository.saveAndFlush(requestData);

        return RegisterResponse.builder()
                .email(user.getEmail())
                .role(role.getRole())
                .build();
    }
    @Transactional
    @Override
    public RegisterResponse registerAdminUser(AuthRequest<Admin> request) {
        Role role = roleService.getOrSave(Role.ERole.ROLE_ADMIN);
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        user = appUserRepository.save(user);

//        Admin requestData = request.getData().orElseThrow(
//                () -> new IllegalArgumentException("Customer data is missing")
//        );
//
//        requestData.setAppUser(user);
//        adminRepository.saveAndFlush(requestData);

        return RegisterResponse.builder()
                .email(user.getEmail())
                .role(role.getRole())
                .build();
    }

    @Transactional
    @Override
    public LoginResponse login(AuthRequest<String> request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        Authentication authenticate = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authenticate.getPrincipal();

        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRoles())
                .build();

    }

    private Customer convertToCustomer(CustomerRequest request) {
        return Customer.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .phone(request.getPhone())
                .status(request.getStatus())
                .build();
    }
}
