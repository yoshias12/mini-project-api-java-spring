package com.enigmacamp.loanApp.service.impl;

import com.enigmacamp.loanApp.model.entity.Role;
import com.enigmacamp.loanApp.repository.RoleRepository;
import com.enigmacamp.loanApp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
private final RoleRepository roleRepository;
    @Override
    public Role getOrSave(Role.ERole role) {
        Optional<Role> optionalRole = roleRepository.findByRole(role);
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }
        Role currentRole = Role.builder()
                .role(role)
                .build();
        return roleRepository.saveAndFlush(currentRole);
    }
}
