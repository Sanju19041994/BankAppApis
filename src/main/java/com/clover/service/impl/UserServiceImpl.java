package com.clover.service.impl;

import com.clover.constants.MyConstants;
import com.clover.dtos.UserDto;
import com.clover.exception.ResourceNotFoundException;
import com.clover.pojo.User;
import com.clover.repository.UserRepo;
import com.clover.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        log.info("saveUser() started from UserServiceImpl class");
        User user = modelMapper.map(userDto, User.class);
        if(user.getActive()==null){
            user.setActive(true);
        }
        if(user.getRole()==null){
            user.setRole("User");
        }else {
            user.setRole("Admin");
        }
        User savedUser = userRepo.save(user);
        log.info("saveUser() completed from UserServiceImpl class");
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        log.info("updateUser() started from UserServiceImpl class");
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(MyConstants.USER_N_F_ID));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDob(userDto.getDob());
        user.setCity(userDto.getCity());
        user.setState(userDto.getState());
        user.setMobile(user.getMobile());

        User updatedUser = userRepo.save(user);
        log.info("updateUser() completed from UserServiceImpl class");
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUser(Integer userId) {
        log.info("getUser() started from UserServiceImpl class");
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(MyConstants.USER_N_F_ID));
        log.info("getUser() completed from UserServiceImpl class");
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto userByEmail(String email) {
        log.info("userByEmail() started from UserServiceImpl class");
        User user = userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(MyConstants.USER_N_F_EMAIL));
        log.info("userByEmail() completed from UserServiceImpl class");
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto userByPan(String panNumber) {
        log.info("userByPan() started from UserServiceImpl class");
        User user = userRepo.findByPanNumber(panNumber).orElseThrow(() -> new ResourceNotFoundException(MyConstants.USER_N_F_PAN));
        log.info("userByPan() completed from UserServiceImpl class");
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto userByAadhar(String aadharNumber) {
        log.info("userByAadhar() started from UserServiceImpl class");
        User user = userRepo.findByAadharNumber(aadharNumber).orElseThrow(() -> new ResourceNotFoundException(MyConstants.USER_N_F_AADHAR));
        log.info("userByAadhar() completed from UserServiceImpl class");
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        log.info("getUsers() started from UserServiceImpl class");
        List<User> all = userRepo.findAll();
        List<UserDto> userDtoList = all.stream().map((list) -> this.modelMapper.map(list, UserDto.class))
                                       .collect(Collectors.toList());
        log.info("getUsers() completed from UserServiceImpl class");
        return userDtoList;
    }

    @Override
    public List<UserDto> searchNameContaining(String keywords) {
        log.info("searchNameContaining() started from UserServiceImpl class");
        List<User> users = userRepo.findByFirstNameContaining(keywords).orElseThrow(() -> new ResourceNotFoundException("No user found with given keywords"));
        List<UserDto> userDtos = users.stream().map((list) -> this.modelMapper.map(list, UserDto.class))
                                       .collect(Collectors.toList());
        log.info("searchNameContaining() completed from UserServiceImpl class");
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        log.info("deleteUser() started from UserServiceImpl class");
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(MyConstants.USER_N_F_ID));
        userRepo.delete(user);
        log.info("deleteUser() completed from UserServiceImpl class");
    }
}
