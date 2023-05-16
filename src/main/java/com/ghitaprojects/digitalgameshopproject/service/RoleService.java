package com.ghitaprojects.digitalgameshopproject.service;

import com.ghitaprojects.digitalgameshopproject.entity.Role;
import com.ghitaprojects.digitalgameshopproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    public Role findById(int id) {

        return roleRepository.findById(id).orElse(null);
    }

    public List<Role> findAll() {

        return roleRepository.findAll();
    }

    public Role findByRoleName(String roleName) {

        return (Role) roleRepository.findByRoleName(roleName);
    }

    public Role save(Role role) {

        return roleRepository.save(role);
    }

    public void delete(Role role) {

        roleRepository.delete(role);
    }


}