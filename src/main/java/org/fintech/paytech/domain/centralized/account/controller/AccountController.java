package org.fintech.paytech.domain.centralized.account.controller;

import org.fintech.paytech.domain.centralized.account.dto.AccountByUserResponseDTO;
import org.fintech.paytech.domain.centralized.account.dto.CreateAccountRequestDTO;
import org.fintech.paytech.domain.centralized.account.dto.CreateAccountResponseDTO;
import org.fintech.paytech.domain.centralized.account.dto.UpdateAccountRequestDTO;
import org.fintech.paytech.domain.centralized.account.model.Account;
import org.fintech.paytech.domain.centralized.account.service.AccountService;
import org.fintech.paytech.domain.centralized.user.model.User;
import org.fintech.paytech.domain.centralized.user.service.AuthService;
import org.fintech.paytech.dto.GenericResponse;
import org.fintech.paytech.domain.centralized.account.dto.AccountDTO;
// import org.fintech.budget.mapper.account.AccountMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final ModelMapper modelMapper;

    public AccountController(AccountService accountService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "")
    public ResponseEntity<GenericResponse<CreateAccountResponseDTO>> createAccount(@Validated @RequestBody CreateAccountRequestDTO accountRequest) {
        CreateAccountResponseDTO responseDTO = accountService.createAccount(accountRequest);
        if (responseDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(responseDTO)
                );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GenericResponse<AccountDTO>> getAccountById(@PathVariable Long id) {
        AccountDTO accountDTO = accountService.findAccountById(id);
        if (accountDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } //User user = userService.findUserByUserName(username).orElseThrow(UserNotFoundException::new);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GenericResponse.builder<AccountDTO>()
                        .success(Boolean.TRUE)
                        .message("Success")
                        .data(accountDTO)
                        .build()
                );
    }

    @GetMapping(path = "")
    public ResponseEntity<GenericResponse<List<AccountDTO>>> getAllAccounts() {
        List<AccountDTO> accountDTOS = accountService.findAllAccountsByUser();
        if (accountDTOS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(accountDTOS));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<GenericResponse<AccountDTO>> updateAccount(@PathVariable Long id, @Validated @RequestBody UpdateAccountRequestDTO accountRequest) {
        AccountDTO accountDTO = accountService.findAccountById(id);
        if (accountDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        AccountDTO updatedAccount = accountService.updateAccount(id, accountRequest);
        if (updatedAccount == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(updatedAccount)
                );
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @GetMapping(path = "/user")
    public ResponseEntity<GenericResponse<List<AccountByUserResponseDTO>>> getAccountsByUser() {
        List<AccountDTO> accountDTOS = accountService.findAllAccountsByUser();
        if (accountDTOS.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<AccountByUserResponseDTO> dtos = accountDTOS.stream()
                .map(accountDTO -> modelMapper.map(accountDTO, AccountByUserResponseDTO.class))
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(dtos));
    }
}
