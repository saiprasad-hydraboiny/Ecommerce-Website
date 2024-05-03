package com.ecommerce.userservice.service;


import com.ecommerce.userservice.model.SessionsStatus;
import com.ecommerce.userservice.dto.UserDto;
import com.ecommerce.userservice.exception.InvalidCredentialsException;
import com.ecommerce.userservice.exception.UserAlreadyExistsException;
import com.ecommerce.userservice.exception.UserNotFoundException;
import com.ecommerce.userservice.mapper.UserMapper;
import com.ecommerce.userservice.model.Session;
import com.ecommerce.userservice.model.User;
import com.ecommerce.userservice.repository.RoleRepository;
import com.ecommerce.userservice.repository.SessionRepository;
import com.ecommerce.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class AuthService {



    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, SessionRepository sessionRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sessionRepository = sessionRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<UserDto> signUp(String email, String password) {
        //check if email already exists
        Optional<User>checkUser=userRepository.findByEmail(email);
        if(checkUser.isPresent())
        {
            throw new UserAlreadyExistsException("User Already exists with this email,please login");
        }
        User user=new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        return new ResponseEntity<>(UserMapper.userToDtoMapper(user), HttpStatus.OK);
    }

    public ResponseEntity<UserDto> login(String email, String password) {

        //Get the user details from the Db
        Optional<User>userOptional=userRepository.findByEmail(email);
        //check if user exists
        if(userOptional.isEmpty())
        {
            throw new UserNotFoundException("User for the given email Id does not exists, please sign-up to continue");
        }

        User user=userOptional.get();
        //Check if credentials are matched
        if(!bCryptPasswordEncoder.matches(password, user.getPassword()))
        {
            throw new InvalidCredentialsException("Invalid Credentials");
        }

        //Token Generation
        String token= RandomStringUtils.randomAlphabetic(30);

        //First check if they already have a session and then cancel it
        Optional<List<Session>>checkSession=sessionRepository.findByUserIdAndSessionStatus(user.getId(), SessionsStatus.ACTIVE);

        if(!checkSession.isEmpty())
        {
            checkSession.get().forEach(session -> session.setSessionStatus(SessionsStatus.ENDED));
        }
        log.info("Token Session Ended");

        //Session Creation
        Session session=new Session();
        session.setSessionStatus(SessionsStatus.ACTIVE);
        session.setToken(token);
        session.setUser(user);

        //Generating the Response
        UserDto userDto=UserMapper.userToDtoMapper(user);

        //Setting Up the Headers
        MultiValueMapAdapter<String,String>headers=new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE,"auth-token: "+token);
        return new ResponseEntity<>(userDto,headers,HttpStatus.OK);

    }
    public ResponseEntity<List<Session>> getAllSessions() {
        return new ResponseEntity<>(sessionRepository.findAll(),HttpStatus.OK);
    }

}
