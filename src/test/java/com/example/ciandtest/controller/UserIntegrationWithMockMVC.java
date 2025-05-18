package com.example.ciandtest.controller;

import com.example.ciandtest.model.User;
import com.example.ciandtest.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource("classpath:application-test-h2.properties")
class UserIntegrationWithMockMVC {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testCreateAndGetUser() throws Exception {
        /* Integration test:
         * This test verifies that a user can be created
         * via the API and subsequently retrieved based on its ID.
         * It checks that the correct user data is returned.
         */

        //arrange
        User user = new User(1L, "harry", 15);

        //act & assert
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("harry")));

        //act
        Optional<User> savedUser = userRepository.findByUsername("harry");

        assertTrue(savedUser.isPresent());
        mockMvc.perform(get("/users/" + savedUser.get().getId()))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.username", is("harry")));
    }

}