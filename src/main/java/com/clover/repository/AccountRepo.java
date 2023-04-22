package com.clover.repository;

import com.clover.pojo.Account;
import com.clover.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {

    Optional<Account> findByAccountNumber(String accountNumber);

    Optional<List<Account>> findByAccStatusTrue();

    Optional<List<Account>> findByAccStatusFalse();

    Optional<Account> findByUser(User user);

}
