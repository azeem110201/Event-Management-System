package com.azeem.demo.services;

import com.azeem.demo.dto.UsersDTO;
import com.azeem.demo.entity.Users;

import java.util.List;

public interface UsersServiceInterface {
    List<Users> listUsers();
    Users getUserById(int id);
    Users saveUser(Users user);
    void deleteUser(int id);
    List<UsersDTO> getAllUsers();
    Users getUserByUsername(String username);
}
