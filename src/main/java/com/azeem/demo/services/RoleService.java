package com.azeem.demo.services;

import com.azeem.demo.entity.Roles;

import java.util.List;

public interface RoleService {
    List<Roles> listRoles();
    Roles getRoleById(int id);
    void saveRole(Roles role);
    void deleteRole(int id);
}
