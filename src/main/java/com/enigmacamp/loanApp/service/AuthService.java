package com.enigmacamp.loanApp.service;

import com.enigmacamp.loanApp.model.dto.request.AuthRequest;
import com.enigmacamp.loanApp.model.dto.request.CustomerRequest;
import com.enigmacamp.loanApp.model.dto.response.LoginResponse;
import com.enigmacamp.loanApp.model.dto.response.RegisterResponse;
import com.enigmacamp.loanApp.model.entity.Admin;
import com.enigmacamp.loanApp.model.entity.Staff;

public interface AuthService {
    RegisterResponse registerCustomerUser(AuthRequest<CustomerRequest> request);
    RegisterResponse registerStaffUser(AuthRequest<Staff> request);
    RegisterResponse registerAdminUser(AuthRequest<Admin> request);
    LoginResponse login(AuthRequest<String> request);
}
