package com.enigmacamp.loanApp.service.impl;

import com.enigmacamp.loanApp.repository.StaffRepository;
import com.enigmacamp.loanApp.model.entity.Staff;
import com.enigmacamp.loanApp.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;

    @Override
    public Staff getStaffById(String id) {
        return staffRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found"));
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll().stream().toList();
    }

    @Override
    public Staff updateStaff(Staff request) {
        getStaffById(request.getId());
        return staffRepository.saveAndFlush(Staff.builder()
                .name(request.getName())
                .position(request.getPosition())
                .build());
    }
}
