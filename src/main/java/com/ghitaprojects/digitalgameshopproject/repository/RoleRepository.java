package com.ghitaprojects.digitalgameshopproject.repository;

import com.ghitaprojects.digitalgameshopproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(String roleName);




}