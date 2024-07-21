package com.enigmacamp.loanApp.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "mst_staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "position")
    private String position;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", unique = true)
    private AppUser appUser;
}
