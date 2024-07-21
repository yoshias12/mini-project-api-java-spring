package com.enigmacamp.loanApp.service;

import com.enigmacamp.loanApp.model.entity.Role;

public interface RoleService {
    Role getOrSave(Role.ERole role);
}
