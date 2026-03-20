package com.capgemini.bank.management.service;

import com.capgemini.bank.management.entity.Account;
import com.capgemini.bank.management.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    // Create Account
    public Account createAccount(Account account) {
        if (account.getBalance() < 0) {
            throw new RuntimeException("Balance cannot be negative");
        }
        return repository.save(account);
    }

    // Get Account
    public Account getAccount(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    // Get All Accounts
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    // Update Account
    public Account updateAccount(int id, Account updatedAccount) {
        Account acc = getAccount(id);

        acc.setAccountHolderName(updatedAccount.getAccountHolderName());

        if (updatedAccount.getBalance() < 0) {
            throw new RuntimeException("Invalid balance");
        }

        acc.setBalance(updatedAccount.getBalance());

        return repository.save(acc);
    }

    // Delete Account
    public String deleteAccount(int id) {
        Account acc = getAccount(id);
        repository.delete(acc);
        return "Account deleted successfully";
    }

    // Deposit
    public Account deposit(int accountId, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Invalid amount");
        }

        Account acc = getAccount(accountId);
        acc.setBalance(acc.getBalance() + amount);

        return repository.save(acc);
    }

    // Withdraw
    public Account withdraw(int accountId, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Invalid amount");
        }

        Account acc = getAccount(accountId);

        if (acc.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        acc.setBalance(acc.getBalance() - amount);

        return repository.save(acc);
    }
}