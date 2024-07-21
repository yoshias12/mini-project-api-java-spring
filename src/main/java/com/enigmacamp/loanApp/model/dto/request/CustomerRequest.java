package com.enigmacamp.loanApp.model.dto.request;

import com.enigmacamp.loanApp.model.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // Setter Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {
    private String id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phone;
    private String status;
    private AppUser user;
}
