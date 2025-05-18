package com.example.ciandtest.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.example.ciandtest.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.net.URI;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test-h2.properties")
class UserIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateAndGetUserByHttp() {
        /* Integration test:
         * This test verifies the HTTP API for creating a User.
         * It checks the correct status responses and the data returned in the response body.
         */
        User user = new User(null, "harry", 15);

        ResponseEntity<User> postResponse =
                restTemplate.postForEntity("http://localhost:" + port + "/users", user, User.class);

        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        assertNotNull(postResponse.getBody());
        Long userId = postResponse.getBody().getId();

        ResponseEntity<User> getResponse = restTemplate.getForEntity("http://localhost:" + port + "/users/" + userId, User.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertNotNull(getResponse.getBody());
        assertEquals(1L, getResponse.getBody().getId());
        assertEquals("harry", getResponse.getBody().getUsername());
    }

    @Test
    public void testDeleteUserByHttp() {
        /* Integration test:
         * This test verifies the HTTP API for delete a User.
         * It checks the correct status responses for later get calls.
         */
        ResponseEntity<User> getResponseBefore = restTemplate.getForEntity("http://localhost:" + port + "/users/1", User.class);
        assertEquals(HttpStatus.OK, getResponseBefore.getStatusCode());

        restTemplate.delete("http://localhost:" + port + "/users/1");

        ResponseEntity<User> getResponseAfter = restTemplate.getForEntity("http://localhost:" + port + "/users/1", User.class);
        assertEquals(HttpStatus.NOT_FOUND, getResponseAfter.getStatusCode());
    }

    @Test
    public void testUpdateUserByHttp() {
        /* Integration test:
         * This test verifies the HTTP API for update a User.
         * It checks the correct status responses and the data returned in the response body.
         */

        restTemplate.put("http://localhost:" + port + "/users/2", new User(null, "jolie", 15));
        ResponseEntity<User> getResponse = restTemplate.getForEntity("http://localhost:" + port + "/users/2", User.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        assertNotNull(getResponse.getBody());
        assertEquals(2L, getResponse.getBody().getId());
        assertEquals("jolie", getResponse.getBody().getUsername());
        assertEquals(15, getResponse.getBody().getAge());
        assertNull(getResponse.getBody().getTodos());

    }
}


