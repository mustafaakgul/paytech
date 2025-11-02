package org.fintech.paytech.domain.core.customer.service.impl;

import org.fintech.paytech.domain.core.customer.model.Customer;
import org.fintech.paytech.domain.centralized.wallet.model.Wallet;
import org.fintech.paytech.domain.core.customer.repository.CustomerRepository;
import org.fintech.paytech.domain.centralized.wallet.repository.WalletRepository;
import org.fintech.paytech.domain.core.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer createCustomerWallet(Customer customer) {
        Customer _customer = customerRepository.save(customer);

        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(0));
        wallet.setCustomer(_customer);
        walletRepository.save(wallet);
        return customerRepository.save(customer);
    }
}
