package com.enigmacamp.loanApp.service;

import com.enigmacamp.loanApp.model.dto.request.CustomerRequest;
import com.enigmacamp.loanApp.model.dto.response.CustomerResponse;
import com.enigmacamp.loanApp.model.entity.Customer;
import com.enigmacamp.loanApp.model.entity.Staff;

import java.util.List;

public interface StaffService {

    Staff getStaffById(String id);

    List<Staff> getAllStaff();

    Staff updateStaff(Staff request);
}
