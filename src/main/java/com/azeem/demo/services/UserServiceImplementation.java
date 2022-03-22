package com.azeem.demo.services;

import com.azeem.demo.dto.UsersDTO;
import com.azeem.demo.entity.Events;
import com.azeem.demo.entity.Users;
import com.azeem.demo.repository.EventsRepository;
import com.azeem.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UsersServiceInterface{
    @Autowired
    private ModelMapper modelMapper;

    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
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
    public Users saveUser(Users user) {

        userRepository.save(user);

        return user;
    }

    @Override
    public void deleteUser(int id) {

        userRepository.deleteById(id);
    }

    @Override
    public List<UsersDTO> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

    }

    private UsersDTO convertEntityToDto(Users user){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        UsersDTO usersDTO = new UsersDTO();
        usersDTO = modelMapper.map(user, UsersDTO.class);
        return usersDTO;
    }

    private Users convertDtoToEntity(UsersDTO usersDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Users user = new Users();
        user = modelMapper.map(usersDTO, Users.class);
        return user;
    }
}
