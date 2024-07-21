package com.enigmacamp.loanApp.service.impl;

import com.enigmacamp.loanApp.model.entity.Admin;
import com.enigmacamp.loanApp.model.entity.Staff;
import com.enigmacamp.loanApp.repository.AdminRepository;
import com.enigmacamp.loanApp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public Admin getAdminById(String id) {
        return adminRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found"));
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll().stream().toList();
    }

    @Override
    public Admin updateAdmin(Admin request) {
        getAdminById(request.getId());
        return adminRepository.saveAndFlush(Admin.builder()
                .name(request.getName())
                .position(request.getPosition())
                .build());
    }
}
