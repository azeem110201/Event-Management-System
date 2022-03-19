package com.azeem.demo.services;

import com.azeem.demo.entity.Events;
import com.azeem.demo.entity.Users;
import com.azeem.demo.repository.EventsRepository;
import com.azeem.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UsersServiceInterface{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository){

        this.userRepository = userRepository;
    }

    @Override
    public List<Users> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(int id) {
        Optional<Users> result = Optional.of(userRepository.getById(id));

        Users theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find user id - " + id);
        }

        return theUser;
    }

    @Override
    public Users getUserByUsername(String username) {
        Optional<Users> result = Optional.of(userRepository.getUserByUsername(username));

        Users theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find username - " + username);
        }

        return theUser;
    }

    @Override
    public void saveUser(Users user) {

        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {

        userRepository.deleteById(id);
    }
}
