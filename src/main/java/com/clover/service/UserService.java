package com.clover.service;

import com.clover.dtos.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Integer userId);

    UserDto getUser(Integer userId);

    UserDto userByEmail(String email);

    UserDto userByPan(String panNumber);

    UserDto userByAadhar(String aadharNumber);

    List<UserDto> getUsers();

    List<UserDto> searchNameContaining(String keywords);

    void deleteUser(Integer userId);



}
