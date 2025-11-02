package org.fintech.paytech.domain.core.customer.controller;

import org.fintech.paytech.domain.core.customer.model.Customer;
import org.fintech.paytech.domain.core.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@Validated @RequestBody Customer customer) {
        return new ResponseEntity<Customer>(customerService.createCustomerWallet(customer), HttpStatus.CREATED);
    }
}
