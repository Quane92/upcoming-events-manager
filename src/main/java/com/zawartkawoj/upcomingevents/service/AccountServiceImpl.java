package com.zawartkawoj.upcomingevents.service;

import com.zawartkawoj.upcomingevents.entity.Account;
import com.zawartkawoj.upcomingevents.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void addAccount(Account account) {
        accountRepository.save(account);
    }
}
