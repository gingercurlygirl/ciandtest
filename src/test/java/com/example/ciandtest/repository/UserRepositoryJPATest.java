package com.example.ciandtest.repository;

import com.example.ciandtest.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application-test-mysql.properties")
class UserRepositoryJPATest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByName() {
        /* Integration test:
         * This test verifies that a user can be successfully
         * persisted and then retrieved by its name. It checks
         * that the found user matches the expected name.
         */

        //arrange
        User user = new User(null, "hermione", 44);
        entityManager.persistAndFlush(user);

        //act
        Optional<User> foundUser = userRepository.findByUsername("hermione");

        //assert
        assertTrue(foundUser.isPresent());
        assertEquals("hermione", foundUser.get().getUsername());
    }

    @Test
    public void testFindAll(){
        /* Integration test:
         * This test verifies that multiple users can be
         * successfully persisted and then retrieved in a list.
         * It checks that the retrieved list contains all the
         * users that were added.
         */
        //arrange
        User user1 = new User(null, "harry", 13);
        User user2 = new User(null, "ron", 55);
        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();

        //act
        List<User> resultUsers = userRepository.findAll();

        //assert
        assertEquals(7, resultUsers.size());
        assertTrue(resultUsers.contains(user1));
        assertTrue(resultUsers.contains(user2));
    }

}