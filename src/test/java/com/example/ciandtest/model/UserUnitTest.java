package com.example.ciandtest.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserUnitTest {

    @Test
    void testSetAndGetMethodsForUser() {
        /* Unit test:
         * Testing logic in isolation from Spring and the database.
         * This test verifies the functionality of getter and setter
         * methods for User. It checks the default values
         * and ensures that values can be set and retrieved correctly.
         */

        User user = new User();

        //assert default constructor
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getAge());
        assertNull(user.getTodos());

        //act
        user.setId(1L);
        user.setUsername("ivana");
        user.setAge(15);
        user.setTodos(new ArrayList<>());

        //assert
        assertEquals(1L, user.getId());
        assertEquals("ivana", user.getUsername());
        assertEquals(15, user.getAge());
        assertNotNull(user.getTodos());
        assertEquals(0, user.getTodos().size());
    }

}