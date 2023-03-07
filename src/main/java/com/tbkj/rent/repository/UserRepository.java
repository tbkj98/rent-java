package com.tbkj.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.tbkj.rent.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
