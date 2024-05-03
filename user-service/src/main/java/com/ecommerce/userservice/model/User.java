package com.ecommerce.userservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel{
    private String email;
    private String password;
    @ManyToMany  //User may have different roles
    private Set<Role> roles=new HashSet<>();
}
