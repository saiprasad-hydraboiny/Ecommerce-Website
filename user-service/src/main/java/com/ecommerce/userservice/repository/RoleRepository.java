package com.ecommerce.userservice.repository;

import com.ecommerce.userservice.model.Role;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, Id> {

    List<Role>findAllByIdIn(List<Long>roleIds);

}
