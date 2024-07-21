package com.enigmacamp.loanApp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ERole role;

    public enum ERole {
        ROLE_CUSTOMER,
        ROLE_STAFF,
        ROLE_ADMIN
    }
}
