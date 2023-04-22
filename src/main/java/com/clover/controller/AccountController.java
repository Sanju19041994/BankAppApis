package com.clover.controller;

import com.clover.constants.MyConstants;
import com.clover.dtos.AccountDto;
import com.clover.helper.ApiResponse;
import com.clover.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;


    @PostMapping("/open_uId/{userId}")
    public ResponseEntity<String> createAccount(@RequestBody AccountDto accountDto, @PathVariable Integer userId){
        log.info("createAccount() started from AccountController class");
        String accountCreated = accountService.openAccount(accountDto, userId);
        log.info("createAccount() completed from AccountController class");
        return new ResponseEntity<>(accountCreated, HttpStatus.CREATED);
    }

    @PutMapping("/update_aId/{acc_Id}")
    public ResponseEntity<ApiResponse> updateAccount(@RequestBody AccountDto accountDto, @PathVariable Integer acc_Id){
        log.info("updateAccount() started from AccountController class");
        boolean status = accountService.updateAccount(accountDto, acc_Id);
        if(status){
            log.info("updateAccount() completed from AccountController class");
            return new ResponseEntity<>(new ApiResponse(MyConstants.ACC_UPDATE_SUCCESS,true),HttpStatus.OK);
        }else{
            log.info("updateAccount() not completed from AccountController class");
            return new ResponseEntity<>(new ApiResponse(MyConstants.ACC_UPDATE_FAIL,false),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/aId/{acc_Id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Integer acc_Id){
        log.info("getAccountById() started from AccountController class");
        AccountDto accountById = accountService.getAccountById(acc_Id);
        log.info("getAccountById() completed from AccountController class");
        return new ResponseEntity<>(accountById,HttpStatus.FOUND);
    }


    @GetMapping("/aNumber/{accountNumber}")
    public ResponseEntity<AccountDto> getByAccNumber(@PathVariable String accountNumber){
        log.info("getByAccNumber() started from AccountController class");
        AccountDto byAccNumber = accountService.getByAccNumber(accountNumber);
        log.info("getByAccNumber() completed from AccountController class");
        return new ResponseEntity<>(byAccNumber,HttpStatus.FOUND);
    }

    @GetMapping("/uId/{userId}")
    public ResponseEntity<AccountDto> getAccByUser(@PathVariable Integer userId){
        log.info("getAccByUser() started from AccountController class");
        AccountDto accByUser = accountService.getAccByUser(userId);
        log.info("getAccByUser() completed from AccountController class");
        return new ResponseEntity<>(accByUser, HttpStatus.FOUND);
    }

    @DeleteMapping("/aId/{acc_Id}")
    public ResponseEntity<ApiResponse> deleteAccount(@PathVariable Integer acc_Id){
        log.info("deleteAccount() started from AccountController class");
        accountService.deleteAccount(acc_Id);
        log.info("deleteAccount() completed from AccountController class");
        return new ResponseEntity<>(new ApiResponse(MyConstants.DLT_SUCCESS,true),HttpStatus.OK);
    }


    @PatchMapping("/deact_aId/{acc_Id}")
    public ResponseEntity<ApiResponse> deActiveAccount( @PathVariable Integer acc_Id){
        log.info("deActiveAccount() started from AccountController class");
        boolean status = accountService.deActiveAccount(acc_Id);
        if(status){
            log.info("deActiveAccount() completed from AccountController class");
            return new ResponseEntity<>(new ApiResponse(MyConstants.DEACT_SUCCESS,true),HttpStatus.OK);
        }else {
            log.info("deActiveAccount() not completed from AccountController class");
            return new ResponseEntity<>(new ApiResponse(MyConstants.DEACT_FAIL,false),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        log.info("getAllAccounts() started from AccountController class");
        List<AccountDto> allAccounts = accountService.getAllAccounts();
        log.info("getAllAccounts() completed from AccountController class");
        return new ResponseEntity<>(allAccounts,HttpStatus.FOUND);
    }


    @GetMapping("/activeAll")
    public ResponseEntity<List<AccountDto>> allActiveAccounts(){
        log.info("allActiveAccounts() started from AccountController class");
        List<AccountDto> accountDtos = accountService.allActiveAccounts();
        log.info("allActiveAccounts() completed from AccountController class");
        return new ResponseEntity<>(accountDtos,HttpStatus.FOUND);
    }


    @GetMapping("/deActiveAll")
    public ResponseEntity<List<AccountDto>> allDeActiveAccounts(){
        log.info("allDeActiveAccounts() started from AccountController class");
        List<AccountDto> accountDtos = accountService.allDeActiveAccounts();
        log.info("allDeActiveAccounts() completed from AccountController class");
        return new ResponseEntity<>(accountDtos,HttpStatus.FOUND);
    }



}
