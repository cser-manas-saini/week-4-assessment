package com.capgemini.bank.management.repository;

import com.capgemini.bank.management.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
