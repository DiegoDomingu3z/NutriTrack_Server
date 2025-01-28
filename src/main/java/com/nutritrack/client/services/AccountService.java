package com.nutritrack.client.services;

import com.nutritrack.client.models.Account;
import com.nutritrack.client.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        return (Account) accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found!"));

    }
}
