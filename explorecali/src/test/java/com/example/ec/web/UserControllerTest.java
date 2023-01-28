package com.example.ec.web;

import com.example.ec.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

// import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @MockBean
    private UserService service;

    @Test
    public void login() {

    }

    @Test
    public void getAllUsers() {
    }
}