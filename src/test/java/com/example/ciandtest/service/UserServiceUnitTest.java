package com.example.ciandtest.service;

import com.example.ciandtest.model.Todo;
import com.example.ciandtest.model.User;
import com.example.ciandtest.model.UserDTO;
import com.example.ciandtest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserByIdReturnsUser() {
       /* Unit test:
        * This test verifies that the userService can successfully
        * retrieve a user by its ID from the repository. It checks
        * that the correct user is returned and that the repository's
        * `findById` method is invoked with the expected user ID.
        */
        //arrange
        User user= new User(1L, "ivana");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //act
        Optional<UserDTO> result = userService.findById(1L);

        //assert
        assertTrue(result.isPresent());
        assertEquals("ivana", result.get().getUsername());
        verify(userRepository).findById(1L);
    }

    @Test
    public void testDeleteUserById() {
        /* Unit test:
         * This test verifies that a user can be successfully
         * deleted by its ID from the userService. It checks that
         * after invoking the delete method, the user no longer
         * exists in the repository.
         */
        //arrange
        User user = new User(1L, "ivana");
        lenient().when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        //act
        userService.deleteUser(user.getId());

        //assert
        assertFalse(userRepository.existsById(1L));
    }


}