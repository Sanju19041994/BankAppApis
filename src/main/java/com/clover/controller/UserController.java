package com.clover.controller;

import com.clover.constants.MyConstants;
import com.clover.dtos.UserDto;
import com.clover.helper.ApiResponse;
import com.clover.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @PostMapping("/save")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto){
        log.info("saveUser() started from UserController class");
        UserDto savedUser = userService.saveUser(userDto);
        log.info("saveUser() completed from UserController class");
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/update_Id/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
        log.info("updateUser() started from UserController class");
        UserDto updateUser = userService.updateUser(userDto, userId);
        log.info("updateUser() completed from UserController class");
        return new ResponseEntity<>(updateUser,HttpStatus.FOUND);
    }


    @GetMapping("/id/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        log.info("getUserById() started from UserController class");
        UserDto user = userService.getUser(userId);
        log.info("getUserById() completed from UserController class");
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> userByEmail(@PathVariable String email){
        log.info("userByEmail() started from UserController class");
        UserDto user = userService.userByEmail(email);
        log.info("userByEmail() completed from UserController class");
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @GetMapping("/pan/{panNumber}")
    public ResponseEntity<UserDto> userByPan(@PathVariable String panNumber){
        log.info("userByPan() started from UserController class");
        UserDto user = userService.userByPan(panNumber);
        log.info("userByPan() completed from UserController class");
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @GetMapping("/aadhar/{aadharNumber}")
    public ResponseEntity<UserDto> userByAadhar(@PathVariable String aadharNumber){
        log.info("userByAadhar() started from UserController class");
        UserDto user = userService.userByAadhar(aadharNumber);
        log.info("userByAadhar() completed from UserController class");
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        log.info("getAllUsers() started from UserController class");
        List<UserDto> users = userService.getUsers();
        log.info("getAllUsers() completed from UserController class");
        return new ResponseEntity<List<UserDto>>(users,HttpStatus.FOUND);
    }


    @GetMapping("search/{keywords}")
    public ResponseEntity<List<UserDto>> searchByNameKeywords(@PathVariable String keywords){
        log.info("searchByNameKeywords() started from UserController class");
        List<UserDto> users = userService.searchNameContaining(keywords);
        log.info("searchByNameKeywords() completed from UserController class");
        return new ResponseEntity<>(users,HttpStatus.FOUND);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        log.info("deleteUser() started from UserController class");
        userService.deleteUser(userId);
        log.info("deleteUser() completed from UserController class");
        return new ResponseEntity<>(new ApiResponse(MyConstants.DLT_SUCCESS,true),HttpStatus.OK);
    }




}
