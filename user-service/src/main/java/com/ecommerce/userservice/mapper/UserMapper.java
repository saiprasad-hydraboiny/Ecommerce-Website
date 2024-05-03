package com.ecommerce.userservice.mapper;

import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.model.User;

public class UserMapper {

    public static User dtoToUserMapper(UserDto userDto)
    {
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(user.getPassword());
        user.setRoles(user.getRoles());

        return user;
    }

    public static UserDto userToDtoMapper(User user)
    {
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
