
package com.ecom.netcart.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecom.netcart.controller.UserController;
import com.ecom.netcart.exception.InvalidLoginException;
import com.ecom.netcart.model.UserInputModel;
import com.ecom.netcart.model.UserOutputModel;
import com.ecom.netcart.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Test
    public void testAddUser() throws Exception {
        // Arrange
        UserInputModel userInputModel = new UserInputModel();
        userInputModel.setUsername("testuser");
        // Set other properties as needed

        UserOutputModel userOutputModel = new UserOutputModel();
        userOutputModel.setUsername(userInputModel.getUsername());
        // Set other properties as needed

        when(userService.addUser(any(UserInputModel.class))).thenReturn(userOutputModel);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        // Act & Assert
        mockMvc.perform(post("/adduser")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(userInputModel)))
            .andExpect(status().isOk());
        // Add more assertions based on the expected response
    }

    @Test
    public void testLoginInfo() throws Exception {
        // Arrange
        String emailId = "test@example.com";
        String password = "password";

        UserOutputModel userOutputModel = new UserOutputModel();
        userOutputModel.setUsername("testuser");
        // Set other properties as needed

        when(userService.loginInfo(emailId, password)).thenReturn(userOutputModel);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        // Act & Assert
        mockMvc.perform(get("/login/{emailId}", emailId)
            .param("password", password))
            .andExpect(status().isOk());
        // Add more assertions based on the expected response
    }
}
