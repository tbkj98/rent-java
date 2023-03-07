package com.tbkj.rent.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tbkj.rent.mapper.RenterMapper;
import com.tbkj.rent.model.dto.BaseDto;
import com.tbkj.rent.model.dto.CreateRenterDto;
import com.tbkj.rent.model.dto.RenterDto;
import com.tbkj.rent.model.entity.Renter;
import com.tbkj.rent.service.RenterService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("renter")
public class RenterController {

    private final RenterService renterService;
    private final RenterMapper renterMapper;

    @GetMapping
    public ResponseEntity<BaseDto<List<RenterDto>>> getAll(@RequestParam(name = "user-id", required = false) Long userId) {
        List<Renter> renters = renterService.getAll(userId);
        List<RenterDto> rentersDto = renterMapper.mapRentersToRentersDto(renters);
        return ResponseEntity.ok(new BaseDto<List<RenterDto>>(rentersDto, "Renters fetched successfully"));
    }

    @PostMapping
    public ResponseEntity<BaseDto<RenterDto>> create(@RequestBody CreateRenterDto dto) {
        Optional<Renter> optional = renterService.createRenter(dto);
        if (optional.isPresent()) {
            RenterDto renterDto = renterMapper.mapRenterToRenterDto(optional.get());
            return ResponseEntity.ok(new BaseDto<RenterDto>(renterDto, "Renter created successfully"));
        }
        return ResponseEntity.ok(new BaseDto<RenterDto>(null, "Renter creation failed"));
    }

}
