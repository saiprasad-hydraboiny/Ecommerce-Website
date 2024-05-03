package com.ecommerce.userservice.controller;


import com.ecommerce.userservice.dto.RoleDto;
import com.ecommerce.userservice.model.Role;
import com.ecommerce.userservice.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDto request)
    {
        Role role=roleService.createRole(request.getName());
        return new ResponseEntity<>(role, HttpStatus.OK);
    }


}
