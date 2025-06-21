package com.example.restservice;

import com.example.dbmodels.User;
import com.example.utils.ResponseWrapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpMainControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createUserShouldReturnDefaultMessage() throws Exception {
        User testUser = new User();
        testUser.setName("Kingori");
        testUser.setEmail("nganga*****@gmail.com");
        testUser.setPhone("2547******");
        ResponseEntity<ResponseWrapper> response = restTemplate.postForEntity("http://localhost:" + port + "/users/add"
                , testUser, ResponseWrapper.class);
        assertThat(response.getBody().getMessage()).contains("Request accepted Successfully");
    }

    @Test
    @Disabled
    void duplicateEmailReturnsError() throws Exception {
        User testUser = new User();
        testUser.setName("Nganga");
        testUser.setEmail("nganga*****@gmail.com");
        testUser.setPhone("2547******");

        ResponseEntity<ResponseWrapper> response = restTemplate.postForEntity("http://localhost:" + port + "/users/add"
                , testUser, ResponseWrapper.class);
        assertThat(response.getBody().getResponseCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}