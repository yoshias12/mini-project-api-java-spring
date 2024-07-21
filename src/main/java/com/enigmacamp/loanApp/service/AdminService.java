package com.enigmacamp.loanApp.service;

import com.enigmacamp.loanApp.model.entity.Admin;
import com.enigmacamp.loanApp.model.entity.Staff;

import java.util.List;

public interface AdminService {
    Admin getAdminById(String id);

    List<Admin> getAllAdmin();

    Admin updateAdmin(Admin request);
}
