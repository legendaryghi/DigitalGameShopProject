package com.ghitaprojects.digitalgameshopproject.controller;

import com.ghitaprojects.digitalgameshopproject.entity.Role;
import com.ghitaprojects.digitalgameshopproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{roleId}")
    public Object getRoleById(@PathVariable int roleId) {
        Role role = roleService.findById(roleId);
        if (role == null) {
            return "Role not found";
        }
        return role;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public Object createRole(@RequestBody Role role) {
        Role createdRole = roleService.save(role);
        if (createdRole == null) {
            return "Failed to create role";
        }
        return createdRole;
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{roleId}")
    public Object updateRole(@PathVariable int roleId, @RequestBody Role role) {
        Role existingRole = roleService.findById(roleId);
        if (existingRole == null) {
            return "Role not found";
        }
        role.setRoleId(roleId);
        Role updatedRole = roleService.save(role);
        if (updatedRole == null) {
            return "Failed to update role";
        }
        return updatedRole;
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{roleId}")
    public Object deleteRole(@PathVariable int roleId) {
        Role role = roleService.findById(roleId);
        if (role == null) {
            return "Role not found";
        }
        roleService.delete(role);
        return "Role deleted successfully";
    }
}







