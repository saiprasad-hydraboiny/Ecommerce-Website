package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.mapper.UserMapper;
import com.ecommerce.userservice.model.Role;
import com.ecommerce.userservice.model.User;
import com.ecommerce.userservice.repository.RoleRepository;
import com.ecommerce.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public User createUser(User user) {

//        User user= UserMapper.dtoToUserMapper(user);
        userRepository.save(user);
        return user;
    }

    public List<UserDto>getAllUsers()
    {
        List<User>users=userRepository.findAll();
        List<UserDto>dtos=users.stream().map(UserMapper::userToDtoMapper).toList();

        return dtos;
    }

    public UserDto getUserDetails(Long userId) {

        Optional<User>optionalUser=userRepository.findById(userId);
        if(optionalUser.isEmpty()) return null;

        return UserDto.from(optionalUser.get());
    }

    public UserDto setUserRoles(Long userId, List<Long> roleIds) {
        Optional<User>optionalUser=userRepository.findById(userId);
        List<Role>roles=roleRepository.findAllByIdIn(roleIds);

        if(optionalUser.isEmpty())
        {
            return null;
        }

        User user=optionalUser.get();
        user.setRoles(Set.copyOf(roles));

        User savedUser=userRepository.save(user);
        return UserDto.from(savedUser);

    }
}
