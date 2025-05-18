package com.example.ciandtest.model;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TodoUnitTest {

    @Test
    void testSetAndGetMethodsForUser() {
        /* Unit test:
         * Testing logic in isolation from Spring and the database.
         * This test verifies the functionality of getter and setter
         * methods for Todo. It checks the default values
         * and ensures that values can be set and retrieved correctly.
         */

        Todo todo = new Todo();

        //assert default constructor
        assertNull(todo.getId());
        assertNull(todo.getTask());
        assertNull(todo.getCreate_date());
        assertNull(todo.isCompleted());
        assertNull(todo.getUser());

        //act
        todo.setId(123L);
        todo.setTask("buy milk");
        LocalDate now = LocalDate.now();
        todo.setCreate_date(Date.valueOf(now));
        todo.setCompleted(true);
        todo.setUser(new User());

        //assert
        assertEquals(123L, todo.getId());
        assertEquals("buy milk", todo.getTask());
        assertEquals(now, todo.getCreate_date().toLocalDate());
        assertTrue(todo.isCompleted());
        assertNotNull(todo.getUser());
    }
}