package com.clover.controller;

import com.clover.constants.MyConstants;
import com.clover.dtos.UserDto;
import com.clover.helper.ApiResponse;
import com.clover.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/save")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto){
        UserDto savedUser = userService.saveUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/update_Id/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
        UserDto updateUser = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updateUser,HttpStatus.FOUND);
    }


    @GetMapping("/id/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        UserDto user = userService.getUser(userId);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> userByEmail(@PathVariable String email){
        UserDto user = userService.userByEmail(email);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @GetMapping("/pan/{panNumber}")
    public ResponseEntity<UserDto> userByPan(@PathVariable String panNumber){
        UserDto user = userService.userByPan(panNumber);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @GetMapping("/aadhar/{aadharNumber}")
    public ResponseEntity<UserDto> userByAadhar(@PathVariable String aadharNumber){
        UserDto user = userService.userByAadhar(aadharNumber);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getUsers();
        return new ResponseEntity<List<UserDto>>(users,HttpStatus.FOUND);
    }


    @GetMapping("search/{keywords}")
    public ResponseEntity<List<UserDto>> searchByNameKeywords(@PathVariable String keywords){
        List<UserDto> users = userService.searchNameContaining(keywords);
        return new ResponseEntity<>(users,HttpStatus.FOUND);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse(MyConstants.DLT_SUCCESS,true),HttpStatus.OK);
    }




}
