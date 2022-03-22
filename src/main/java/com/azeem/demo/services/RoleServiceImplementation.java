package com.azeem.demo.services;

import com.azeem.demo.entity.Roles;
import com.azeem.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImplementation implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImplementation(RoleRepository roleRepository){

        this.roleRepository = roleRepository;
    }

    @Override
    public List<Roles> listRoles() {

        return roleRepository.findAll();
    }

    @Override
    public Roles getRoleById(int id) {
        Optional<Roles> result = Optional.of(roleRepository.getById(id));

        Roles theRole = null;

        if (result.isPresent()) {
            theRole = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find role id - " + id);
        }

        return theRole;
    }

    @Override
    public void saveRole(Roles role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }
}
