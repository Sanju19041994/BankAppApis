package com.clover.service;

import com.clover.dtos.AccountDto;

import java.util.List;

public interface AccountService {


    String openAccount(AccountDto accountDto, Integer userId);

    boolean updateAccount(AccountDto accountDto, Integer acc_Id);

    AccountDto getAccountById(Integer acc_Id);

    AccountDto getByAccNumber(String accountNumber);

    AccountDto getAccByUser(Integer userId);

    void deleteAccount(Integer acc_Id);

    boolean deActiveAccount(Integer acc_Id);

    List<AccountDto> getAllAccounts();

    List<AccountDto> allActiveAccounts();

    List<AccountDto> allDeActiveAccounts();

}
