package com.ecommerce.userservice.controller;


import com.ecommerce.userservice.dto.SetUserRolesRequestDto;
import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.model.User;
import com.ecommerce.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<String>createUser(@RequestBody User user)
    {
        User userResponse = userService.createUser(user);
        if(userResponse==null)
        {
            return new ResponseEntity<>("User not created, please try again", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User Created Successfully",HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto>getUserDetails(@PathVariable("id")Long userId)
    {
        UserDto userDto=userService.getUserDetails(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }


    @PostMapping(path = "/{id}/roles")
    public ResponseEntity<UserDto>setUserRoles(@PathVariable("id") Long userId,@RequestBody SetUserRolesRequestDto request )
    {
        UserDto userDto=userService.setUserRoles(userId,request.getRoleIds());
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }


}
