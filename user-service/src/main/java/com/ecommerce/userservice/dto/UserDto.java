package com.ecommerce.userservice.dto;


import com.ecommerce.userservice.model.Role;
import com.ecommerce.userservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String email;
    private Set<Role> roles=new HashSet<>();

    public static UserDto from(User user)
    {
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }

}
