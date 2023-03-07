package com.tbkj.rent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tbkj.rent.mapper.UserMapper;
import com.tbkj.rent.model.dto.CreateUserDto;
import com.tbkj.rent.model.entity.User;
import com.tbkj.rent.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService , UserDetailsService{

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public Optional<User> findUserById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User createUser(CreateUserDto dto) {
        User user = mapper.mapCreateUserDtoToUser(dto);
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = getUserByEmail(username);
        if(optional.isPresent()) {
            return optional.get();
        }
        throw new UsernameNotFoundException(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
       return repository.findUserByEmail(email);
    }

}
