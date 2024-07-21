package com.enigmacamp.loanApp.service.impl;

import com.enigmacamp.loanApp.model.dto.request.CustomerRequest;
import com.enigmacamp.loanApp.model.dto.response.CustomerResponse;
import com.enigmacamp.loanApp.model.entity.AppUser;
import com.enigmacamp.loanApp.model.entity.Customer;
import com.enigmacamp.loanApp.repository.AppUserRepository;
import com.enigmacamp.loanApp.repository.CustomerRepository;
import com.enigmacamp.loanApp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;


    @Override
    public Customer getCustomerById(String id) {
        return findByidOrThrowNotFound(id);
    }

    @Override
    public List<CustomerResponse> getAllCustomer() {
        return customerRepository.findAll().stream().map(this::convertToCustomerResponse).toList();
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest request) {
        findByidOrThrowNotFound(request.getId());
        Customer customer = customerRepository.saveAndFlush(
                Customer.builder()
                        .id(request.getId())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .phone(request.getPhone())
                        .dateOfBirth(request.getDateOfBirth())
                        .status(request.getStatus())
                        .build()
        );
        return convertToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse deleteCustomerById(String id) {
       Customer customer = findByidOrThrowNotFound(id);
       customer.setStatus("UnActived");
       customerRepository.saveAndFlush(customer);
       return convertToCustomerResponse(customer);
    }

    private CustomerResponse convertToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .dateOfBirth(customer.getDateOfBirth())
                .phone(customer.getPhone())
                .status(customer.getStatus())
                .build();
    }

    private Customer findByidOrThrowNotFound(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found"));
    }
}
