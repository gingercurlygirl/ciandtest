package com.example.ciandtest.service;

import com.example.ciandtest.model.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@TestPropertySource("classpath:application-test-h2.properties")
class UserServiceComponentTest {
    @Autowired
    private UserService userService;


    @Test
    public void testCreateAndFetchUser() {
        /* Integration test:
         * This test verifies that a user can be successfully created
         * and subsequently retrieved by its ID. It checks that the
         * created user's details match the fetched channel.
         */
        //arrange
        User user = new User(1L, "ivana");

        //act
        userService.addUser(user);
        Optional<User> fetchedUser = userService.findById(1L);

        //assert
        assertTrue(fetchedUser.isPresent());
        assertEquals(1L, fetchedUser.get().getId());
        assertEquals("ivana", fetchedUser.get().getUsername());
    }

}