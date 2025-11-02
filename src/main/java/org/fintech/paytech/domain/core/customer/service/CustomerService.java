package org.fintech.paytech.domain.core.customer.service;

import org.fintech.paytech.domain.core.customer.model.Customer;

public interface CustomerService {

    public Customer createCustomer(Customer customer);

    public Customer createCustomerWallet(Customer customer);
}
