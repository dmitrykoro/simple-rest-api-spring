package com.paper_submission_system.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.paper_submission_system.api.model.AccountModel;
import com.paper_submission_system.api.repository.AccountRepository;


@RestController
@RequestMapping("/api/v1/")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/accounts/create")
    public AccountModel createAccount(@RequestBody AccountModel account) {
        return accountRepository.save(account);
    }

    // other methods for account operations may be implemented similarly to PaperController
}
