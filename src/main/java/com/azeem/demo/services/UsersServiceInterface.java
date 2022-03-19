package com.azeem.demo.services;

import com.azeem.demo.entity.Users;

import java.util.List;

public interface UsersServiceInterface {
    List<Users> listUsers();
    Users getUserById(int id);
    void saveUser(Users user);
    void deleteUser(int id);
    Users getUserByUsername(String username);
}
