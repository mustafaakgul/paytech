package org.fintech.paytech.domain.centralized.account.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.fintech.paytech.domain.core.account.dto.AccountDTO;
import org.fintech.paytech.domain.core.account.dto.CreateAccountRequestDTO;
import org.fintech.paytech.domain.core.account.dto.CreateAccountResponseDTO;
import org.fintech.paytech.domain.core.account.dto.UpdateAccountRequestDTO;
import org.fintech.paytech.domain.core.account.model.Account;
import org.fintech.paytech.domain.core.account.repository.AccountRepository;
import org.fintech.paytech.domain.core.account.service.AccountService;
import org.fintech.paytech.domain.core.user.model.User;
import org.fintech.paytech.domain.core.user.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AuthService authService;
    private final ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AuthService authService, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateAccountResponseDTO createAccount(CreateAccountRequestDTO requestDTO) {
        Optional<User> user = authService.findAuthenticatedUser();
        if (user.isEmpty()){
            return null;
        }

        Account account = new Account(
                requestDTO.getName(),
                requestDTO.getDescription(),
                requestDTO.getCurrency(),
                requestDTO.getBalance(),
                requestDTO.getType()
        );
        account.setUser(user.get());

        Account _account = accountRepository.save(account);
        CreateAccountResponseDTO responseDTO = modelMapper.map(_account, CreateAccountResponseDTO.class);
        return responseDTO;
    }

    @Override
    public AccountDTO findAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) {
            return null;
        }

        AccountDTO responseDTO = modelMapper.map(account.get(), AccountDTO.class);
        return responseDTO;
    }

    @Override
    public Account findInternalAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) return null;

        return account.get();
    }

    @Override
    public List<AccountDTO> findAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .toList(); // .collect(Collectors.toList());
    }

    @Override
    public AccountDTO updateAccount(Long id, UpdateAccountRequestDTO updateAccountRequestDTO) {
        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (existingAccount == null) return null;

        existingAccount.setName(updateAccountRequestDTO.getName());
        existingAccount.setDescription(updateAccountRequestDTO.getDescription());
        existingAccount.setCurrency(updateAccountRequestDTO.getCurrency());
        existingAccount.setBalance(updateAccountRequestDTO.getBalance());
        existingAccount.setType(updateAccountRequestDTO.getType());

        Account account = accountRepository.save(existingAccount);
        return modelMapper.map(account, AccountDTO.class);
    }

    @Override
    public void deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new EntityNotFoundException("Account not found with id: " + id);
        }
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDTO> findAllAccountsByUserId(Long userId) {
        Optional<User> user = authService.findAuthenticatedUser();
        if (user.isEmpty()){
            return List.of();
        }

        List<Account> accounts = accountRepository.findByUserId(userId);
        return accounts.stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .toList();
    }

    @Override
    public List<AccountDTO> findAllAccountsByUser() {
        Optional<User> user = authService.findAuthenticatedUser();
        if (user.isEmpty()){
            return List.of();
        }

        List<Account> accounts = accountRepository.findByUserId(user.get().getId());
        return accounts.stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .toList();
    }
}
