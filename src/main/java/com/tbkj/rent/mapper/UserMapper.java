package com.tbkj.rent.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tbkj.rent.model.dto.CreateUserDto;
import com.tbkj.rent.model.dto.UserDto;
import com.tbkj.rent.model.entity.User;

@Mapper
public interface UserMapper {
    
    @Mapping(target = "id", source = "id")
    UserDto mapUserToUserDto(User user);

    User mapCreateUserDtoToUser(CreateUserDto dto);
}
