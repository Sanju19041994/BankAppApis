package com.clover.service.impl;

import com.clover.constants.MyConstants;
import com.clover.dtos.AccountDto;
import com.clover.exception.ResourceNotFoundException;
import com.clover.pojo.Account;
import com.clover.pojo.User;
import com.clover.repository.AccountRepo;
import com.clover.repository.UserRepo;
import com.clover.service.AccountService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AccountRepo accountRepo;


    @Autowired
    private ModelMapper modelMapper;



    @Override
    public String openAccount(AccountDto accountDto, Integer userId) {
        log.info("openAccount() started from AccountServiceImpl class");
        Account account = modelMapper.map(accountDto, Account.class);
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(MyConstants.USER_N_F_ID));

        /*
        // Create Account Number with Random
        String rangDigit = "0123456789";
        int acc_Length = 6;
        char [] num = new char[acc_Length];

        Random r = new Random();
        Integer random = r.nextInt(acc_Length);
        for(int i=0; i<acc_Length; i++)
        {
            num [i] = rangDigit.charAt(random);
        }

        String createdAccNumber = new String(num);

         */
        // Create Account Number by Time
        Calendar cal = Calendar.getInstance();
        String createdAccNumber = new SimpleDateFormat(MyConstants.ACC_PATTERN).format(cal.getTime());

        account.setUser(user);
        account.setAccountNumber(createdAccNumber);
        account.setAccStatus(true);

        Account savedAccount = accountRepo.save(account);

        log.info("openAccount() completed from AccountServiceImpl class");
        return MyConstants.ACC_CREATED+account.getAccountNumber();
    }

    @Override
    public boolean updateAccount(AccountDto accountDto, Integer acc_Id) {
        log.info("updateAccount() started from AccountServiceImpl class");
        Account account = accountRepo.findById(acc_Id).orElseThrow(() -> new ResourceNotFoundException(MyConstants.ACC_N_F_ID));

        account.setCategory(accountDto.getCategory());
        account.setBranch(accountDto.getBranch());
        account.setIfscCode(accountDto.getIfscCode());

        if(account != null){
            accountRepo.save(account);
            log.info("updateAccount() completed from AccountServiceImpl class");
            return true;
        }else {
            log.info("updateAccount() not completed from AccountServiceImpl class");
            return false;
        }
    }

    @Override
    public AccountDto getAccountById(Integer acc_Id) {
        log.info("getAccountById() started from AccountServiceImpl class");
        Account account = accountRepo.findById(acc_Id).orElseThrow(() -> new ResourceNotFoundException(MyConstants.ACC_N_F_ID));
        log.info("getAccountById() completed from AccountServiceImpl class");
        return modelMapper.map(account,AccountDto.class);
    }

    @Override
    public AccountDto getByAccNumber(String accountNumber) {
        log.info("getByAccNumber() started from AccountServiceImpl class");
        Account account = accountRepo.findByAccountNumber(accountNumber).orElseThrow(() -> new ResourceNotFoundException(MyConstants.ACC_N_F_ACC_NUM));
        log.info("getByAccNumber() completed from AccountServiceImpl class");
        return modelMapper.map(account, AccountDto.class);
    }

    @Override
    public AccountDto getAccByUser(Integer userId) {
        log.info("getAccByUser() started from AccountServiceImpl class");
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(MyConstants.USER_N_F_ID));
        Account account = accountRepo.findByUser(user).orElseThrow(() -> new ResourceNotFoundException(MyConstants.ACC_N_F_USER));
        log.info("getAccByUser() completed from AccountServiceImpl class");
        return modelMapper.map(account, AccountDto.class);
    }

    @Override
    public void deleteAccount(Integer acc_Id) {
        log.info("deleteAccount() started from AccountServiceImpl class");
        Account account = accountRepo.findById(acc_Id).orElseThrow(() -> new ResourceNotFoundException(MyConstants.ACC_N_F_ID));
        accountRepo.delete(account);
        log.info("deleteAccount() completed from AccountServiceImpl class");
    }

    @Override
    public boolean deActiveAccount(Integer acc_Id) {
        log.info("deActiveAccount() started from AccountServiceImpl class");
        Account account = accountRepo.findById(acc_Id).orElseThrow(() -> new ResourceNotFoundException(MyConstants.ACC_N_F_ID));
        if(account != null)
        {
            account.setAccStatus(false);
            log.info("deActiveAccount() completed from AccountServiceImpl class");
            return true;
        }
        log.info("deActiveAccount() not completed from AccountServiceImpl class");
        return false;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        log.info("getAllAccounts() started from AccountServiceImpl class");
        List<Account> all = accountRepo.findAll();
        List<AccountDto> listOfAcc = all.stream().map((list) -> modelMapper.map(list, AccountDto.class)).collect(Collectors.toList());
        log.info("getAllAccounts() completed from AccountServiceImpl class");
        return listOfAcc;
    }

    @Override
    public List<AccountDto> allActiveAccounts() {
        log.info("allActiveAccounts() started from AccountServiceImpl class");
        List<Account> all = accountRepo.findByAccStatusTrue().orElseThrow(() -> new ResourceNotFoundException(MyConstants.ACC_STATUS_TRUE));
        List<AccountDto> activeAcc = all.stream().map((list) -> modelMapper.map(list, AccountDto.class)).collect(Collectors.toList());
        log.info("allActiveAccounts() completed from AccountServiceImpl class");
        return activeAcc;
    }

    @Override
    public List<AccountDto> allDeActiveAccounts() {
        log.info("allDeActiveAccounts() started from AccountServiceImpl class");
        List<Account> all = accountRepo.findByAccStatusFalse().orElseThrow(() -> new ResourceNotFoundException(MyConstants.ACC_STATUS_FALSE));
        List<AccountDto> deActiveAcc = all.stream().map((list) -> modelMapper.map(list, AccountDto.class)).collect(Collectors.toList());
        log.info("allDeActiveAccounts() completed from AccountServiceImpl class");
        return deActiveAcc;
    }


}
