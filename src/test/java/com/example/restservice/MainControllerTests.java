package com.example.restservice;

import com.example.dbmodels.User;
import com.example.dtos.CreateUserDTO;
import com.example.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setName("Nganga");
        userDTO.setPhone("2547*******");
        userDTO.setEmail("nganga***@gmail.com");
        this.mockMvc.perform(post("/users/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Request accepted Successfully"));
    }

    @Test
    void shouldFetchAllUsers() throws Exception {
        Page<User> users;
        List<User> userList= new ArrayList<>();
        users = new PageImpl<>(userList, PageRequest.of(0, 4), userList.size());
        when(userRepository.findAll(any(Pageable.class))).thenReturn(users);

        this.mockMvc.perform(get("/users/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data",hasSize(0)))
                .andExpect(jsonPath("$.data[0]").doesNotExist());
    }

}
