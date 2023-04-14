package com.clover.service.impl;

import com.clover.constants.MyConstants;
import com.clover.dtos.UserDto;
import com.clover.exception.ResourceNotFoundException;
import com.clover.pojo.User;
import com.clover.repository.UserRepo;
import com.clover.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(MyConstants.USER_N_F_ID));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDob(userDto.getDob());
        user.setCity(userDto.getCity());
        user.setState(userDto.getState());
        user.setMobile(user.getMobile());

        User updatedUser = userRepo.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(MyConstants.USER_N_F_ID));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto userByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(MyConstants.USER_N_F_EMAIL));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto userByPan(String panNumber) {
        User user = userRepo.findByPanNumber(panNumber).orElseThrow(() -> new ResourceNotFoundException(MyConstants.USER_N_F_PAN));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto userByAadhar(String aadharNumber) {
        User user = userRepo.findByAadharNumber(aadharNumber).orElseThrow(() -> new ResourceNotFoundException(MyConstants.USER_N_F_AADHAR));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> all = userRepo.findAll();
        List<UserDto> userDtoList = all.stream().map((list) -> this.modelMapper.map(list, UserDto.class))
                                       .collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public List<UserDto> searchNameContaining(String keywords) {
        List<User> users = userRepo.findByFirstNameContaining(keywords).orElseThrow(() -> new ResourceNotFoundException("No user found with given keywords"));
        List<UserDto> userDtos = users.stream().map((list) -> this.modelMapper.map(list, UserDto.class))
                                       .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(MyConstants.USER_N_F_ID));
        userRepo.delete(user);
    }
}
