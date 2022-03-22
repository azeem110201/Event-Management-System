package com.azeem.demo.services;

import com.azeem.demo.entity.Roles;
import com.azeem.demo.entity.Users;
import com.azeem.demo.repository.RoleRepository;
import com.azeem.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplementationTest {
    @Autowired
    private UsersServiceInterface usersService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Test
    void listUsers() {
        when(userRepository.findAll()).thenReturn(Stream
                .of(new Users("jack","test123","micheal","jackson",22,"M","IT")).collect(Collectors.toList()));

        assertEquals(1, usersService.listUsers().size());
    }

    @Test
    void saveUser() {
        Users users = new Users("jack","test123","micheal","jackson",22,"M","IT");
        Roles role = roleRepository.getById(2);
        users.addRole(role);

        when(userRepository.save(users)).thenReturn(users);

        assertEquals(users, usersService.saveUser(users));
    }

    @Test
    void deleteUser() {
        Users users = new Users("jack","test123","micheal","jackson",22,"M","IT");
        Roles role = roleRepository.getById(2);
        users.addRole(role);

        usersService.deleteUser(users.getId());
        verify(userRepository, times(1)).deleteById(users.getId());
    }
}