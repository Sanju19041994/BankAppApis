package com.clover.repository;

import com.clover.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo  extends JpaRepository<User,Integer> {

    Optional<User> findByPanNumber(String panNumber);

    Optional<User> findByAadharNumber(String aadharNumber);

    Optional<User> findByEmail(String email);

    Optional<List<User>> findByFirstNameContaining(String keywords);


}
