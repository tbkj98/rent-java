package com.tbkj.rent.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tbkj.rent.mapper.RenterMapper;
import com.tbkj.rent.model.dto.CreateRenterDto;
import com.tbkj.rent.model.entity.Renter;
import com.tbkj.rent.model.entity.User;
import com.tbkj.rent.repository.RenterRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RenterServiceImpl implements RenterService {

    private final RenterRepository repository;
    private final RenterMapper mapper;
    private final UserService userService;
    
    @Override
    public List<Renter> getAll(Long userId) {
    	Optional<User> optional = userService.findUserById(userId);
    	if(optional.isPresent()) {
    		return optional.get().getRenters();
    	}
        return Collections.emptyList();
    }

    @Override
    public Optional<Renter> createRenter(CreateRenterDto dto) {
        Renter renter = mapper.mapCreateRenterDtoToRenter(dto);
        return Optional.of(repository.save(renter));
    }
}
