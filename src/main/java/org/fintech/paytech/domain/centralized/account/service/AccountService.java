package org.fintech.paytech.domain.centralized.account.service;

import org.fintech.paytech.domain.centralized.account.dto.AccountDTO;
import org.fintech.paytech.domain.centralized.account.dto.CreateAccountRequestDTO;
import org.fintech.paytech.domain.centralized.account.dto.CreateAccountResponseDTO;
import org.fintech.paytech.domain.centralized.account.dto.UpdateAccountRequestDTO;
import org.fintech.paytech.domain.centralized.account.model.Account;

import java.util.List;

public interface AccountService {

    CreateAccountResponseDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO);

    AccountDTO findAccountById(Long id);

    Account findInternalAccountById(Long id);

    List<AccountDTO> findAllAccounts();

    AccountDTO updateAccount(Long id, UpdateAccountRequestDTO updateAccountRequestDTO);

    void deleteAccount(Long id);

    List<AccountDTO> findAllAccountsByUserId(Long userId);

    List<AccountDTO> findAllAccountsByUser();
}
