package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.LoginRequestDto;
import com.ecommerce.userservice.dto.SignUpRequestDto;
import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.model.Session;
import com.ecommerce.userservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
;
import java.util.List;


@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService=authService;
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto>signUp(@RequestBody SignUpRequestDto request)
    {
        UserDto userDto=authService.signUp(request.getEmail(),request.getPassword()).getBody();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserDto>login(@RequestBody LoginRequestDto request)
    {
        return authService.login(request.getEmail(),request.getPassword());
    }

    @GetMapping(path = "/sessions")
    public ResponseEntity<List<Session>>getAllSessions()
    {
        return authService.getAllSessions();
    }

}
