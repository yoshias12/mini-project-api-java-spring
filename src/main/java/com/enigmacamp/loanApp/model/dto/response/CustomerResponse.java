package com.enigmacamp.loanApp.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // Setter Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phone;
    private String status;
}
