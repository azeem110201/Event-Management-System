package com.azeem.demo.controller;

import com.azeem.demo.dto.UsersDTO;
import com.azeem.demo.entity.Users;
import com.azeem.demo.services.UsersServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UsersControllerTest {
//    MockMvc mockMvc;
//    ObjectMapper objectMapper;
//    UsersServiceInterface usersService;


//    @BeforeEach
//    void setUp() {
//        usersService = mock(UsersServiceInterface.class);
//        mockMvc = MockMvcBuilders.standaloneSetup(new UsersController(usersService)).build();
//        objectMapper = new ObjectMapper();
//    }

    @Test
    void listUsers() {
//        Users users = new Users("jack", "test123","micheal","jackson",22, "M", "IT");
//        when(usersService.listUsers()).thenReturn(Collections.singletonList(users));
//        mockMvc.perform(get("/api/users/list")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(users))))
//                .andExpect(status().isOk());
    }

    @Test
    void showFormForAdd() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void showFormForUpdate() {
    }

    @Test
    void delete() {
    }
}