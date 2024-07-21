package com.enigmacamp.loanApp.service;

import com.enigmacamp.loanApp.model.dto.request.CustomerRequest;
import com.enigmacamp.loanApp.model.dto.response.CustomerResponse;
import com.enigmacamp.loanApp.model.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer getCustomerById(String id);

    List<CustomerResponse> getAllCustomer();

    CustomerResponse updateCustomer(CustomerRequest request);

    CustomerResponse deleteCustomerById(String id);


}
