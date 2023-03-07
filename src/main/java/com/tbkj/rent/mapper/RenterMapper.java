package com.tbkj.rent.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.tbkj.rent.model.dto.CreateRenterDto;
import com.tbkj.rent.model.dto.RenterDto;
import com.tbkj.rent.model.entity.Renter;

@Mapper
public interface RenterMapper {

    List<RenterDto> mapRentersToRentersDto(List<Renter> renters);

    RenterDto mapRenterToRenterDto(Renter renter);

    Renter mapCreateRenterDtoToRenter(CreateRenterDto dto);
}
