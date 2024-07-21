package com.enigmacamp.loanApp.controller;

import com.enigmacamp.loanApp.model.dto.request.CustomerRequest;
import com.enigmacamp.loanApp.model.dto.response.CommonResponse;
import com.enigmacamp.loanApp.model.dto.response.CustomerResponse;
import com.enigmacamp.loanApp.model.entity.Customer;
import com.enigmacamp.loanApp.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<Customer>> getCustomerById(@PathVariable String id) {
        Customer customerResponse = customerService.getCustomerById(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .message("Successfully fetch user")
                .data(Optional.of(customerResponse))
                .build();
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping()
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getAllCustomer(){
        List<CustomerResponse> customerResponseList = customerService.getAllCustomer();
        CommonResponse<List<CustomerResponse>> response = CommonResponse.<List<CustomerResponse>>builder()
                .message("Successfully fetch user")
                .data(Optional.of(customerResponseList))
                .build();
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER','ROLE_STAFF')")
    @PutMapping
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerRequest request){
        CustomerResponse customerResponse = customerService.updateCustomer(request);
        return ResponseEntity.ok(customerResponse);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER','ROLE_STAFF')")
    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<CustomerResponse>> deleteCustomerById(@PathVariable String id){
        CustomerResponse customer = customerService.deleteCustomerById(id);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
               .message("Successfully soft delete user")
               .data(Optional.of(customer))
               .build();
        return ResponseEntity.ok(response);
    }
}
