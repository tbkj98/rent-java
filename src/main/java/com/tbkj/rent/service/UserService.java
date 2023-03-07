package com.tbkj.rent.service;

import java.util.List;
import java.util.Optional;

import com.tbkj.rent.model.dto.CreateUserDto;
import com.tbkj.rent.model.entity.User;

public interface UserService {

    Optional<User> findUserById(Long id);

    List<User> getAllUsers();

    User createUser(CreateUserDto dto);

    Optional<User> getUserByEmail(String email);
}
