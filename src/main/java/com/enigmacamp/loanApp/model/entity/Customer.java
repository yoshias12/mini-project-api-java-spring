package com.enigmacamp.loanApp.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "mst_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "phone")
    private String phone;
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;
    @ManyToOne
    @JoinColumn(name = "profile_picture_id")
    private ImageCustomer imageCustomer;

}


