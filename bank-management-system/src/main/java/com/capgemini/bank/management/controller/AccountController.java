package com.capgemini.bank.management.controller;

import com.capgemini.bank.management.dto.TransactionRequest;
import com.capgemini.bank.management.entity.Account;
import com.capgemini.bank.management.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return service.createAccount(account);
    }

    // Get Account by ID
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable int id) {
        return service.getAccount(id);
    }

    
    @GetMapping
    public List<Account> getAllAccounts() {
        return service.getAllAccounts();
    }

   
    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable int id, @RequestBody Account account) {
        return service.updateAccount(id, account);
    }

   
    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable int id) {
        return service.deleteAccount(id);
    }

   
    @PostMapping("/deposit")
    public Map<String, Object> deposit(@RequestBody TransactionRequest request) {
        Account acc = service.deposit(request.getAccountId(), request.getAmount());

        return Map.of(
                "message", "Deposit successful",
                "updatedBalance", acc.getBalance()
        );
    }

   
    @PostMapping("/withdraw")
    public Map<String, Object> withdraw(@RequestBody TransactionRequest request) {
        Account acc = service.withdraw(request.getAccountId(), request.getAmount());

        return Map.of(
                "message", "Withdrawal successful",
                "updatedBalance", acc.getBalance()
        );
    }
}
