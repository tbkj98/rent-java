package com.tbkj.rent.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tbkj.rent.mapper.UserMapper;
import com.tbkj.rent.model.dto.BaseDto;
import com.tbkj.rent.model.dto.CreateUserDto;
import com.tbkj.rent.model.dto.UserDto;
import com.tbkj.rent.model.entity.User;
import com.tbkj.rent.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("{id}")
    public ResponseEntity<BaseDto<UserDto>> getById(@PathVariable("id") Long id) {
        Optional<User> optional = userService.findUserById(id);
        BaseDto<UserDto> dto = null;

        if (optional.isPresent()) {
            dto = new BaseDto<>(userMapper.mapUserToUserDto(optional.get()), "User found");
        } else {
            dto = new BaseDto<>(null, "User not found");
        }

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody CreateUserDto dto) {
        try {
            User user = userService.createUser(dto);
            return new ResponseEntity<>(userMapper.mapUserToUserDto(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

}
