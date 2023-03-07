package com.tbkj.rent.service;

import java.util.List;
import java.util.Optional;
import com.tbkj.rent.model.entity.Renter;
import com.tbkj.rent.model.dto.CreateRenterDto;

public interface RenterService {
    List<Renter> getAll(Long userId);
    Optional<Renter> createRenter(CreateRenterDto dto);
}
