package com.ecom.netcart.servicetest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ecom.netcart.entity.User;
import com.ecom.netcart.exception.InvalidLoginException;
import com.ecom.netcart.exception.InvalidRegistrationException;
import com.ecom.netcart.model.UserInputModel;
import com.ecom.netcart.model.UserOutputModel;
import com.ecom.netcart.repository.UserDao;
import com.ecom.netcart.service.UserServiceImpl;
import com.ecom.netcart.util.MapUserRow;
import com.ecom.netcart.validations.UserValidation;
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    private UserServiceImpl userService;
    private UserDao userDaoMock;
    private MapUserRow mapUserRowMock;
    private UserValidation userValidationMock;

    

    @Test
    public void testAddUser_ValidInput_ReturnsUserOutputModel() throws InvalidRegistrationException {
        // Arrange
        UserInputModel inputModel = new UserInputModel();
        inputModel.setUsername("john");
        inputModel.setAddress("123 Main St");
        inputModel.setEmail("john@example.com");
        inputModel.setPassword("password");

        User user = new User();
        user.setUsername(inputModel.getUsername());
        user.setAddress(inputModel.getAddress());
        user.setEmail(inputModel.getEmail());
        user.setPassword(inputModel.getPassword());

        UserOutputModel outputModel = new UserOutputModel();
        outputModel.setUsername(user.getUsername());
        outputModel.setAddress(user.getAddress());
        outputModel.setEmail(user.getEmail());

        when(userValidationMock.checkEmail(inputModel.getEmail())).thenReturn(true);
        when(userValidationMock.validatePassword(inputModel.getPassword())).thenReturn(true);
        when(userValidationMock.checkName(inputModel.getUsername())).thenReturn(true);
        when(userDaoMock.save(any(User.class))).thenReturn(user);
        when(mapUserRowMock.mapToUserOutput(user)).thenReturn(outputModel);

        // Act
        UserOutputModel result = userService.addUser(inputModel);

        // Assert
        assertNotNull(result);
        assertEquals(outputModel.getUsername(), result.getUsername());
        assertEquals(outputModel.getAddress(), result.getAddress());
        assertEquals(outputModel.getEmail(), result.getEmail());
    }

    

    @Test
    public void testLoginInfo_ValidCredentials_ReturnsUserOutputModel() throws InvalidLoginException {
        // Arrange
        String emailId = "john@example.com";
        String password = "password";

        User user = new User();
        user.setUsername("john");
        user.setAddress("123 Main St");
        user.setEmail(emailId);
        user.setPassword(password);

        UserOutputModel outputModel = new UserOutputModel();
        outputModel.setUsername(user.getUsername());
        outputModel.setAddress(user.getAddress());
        outputModel.setEmail(user.getEmail());

        when(userDaoMock.getEmail(emailId)).thenReturn(user);
        when(mapUserRowMock.mapToUserOutput(user)).thenReturn(outputModel);

        // Act
        UserOutputModel result = userService.loginInfo(emailId, password);

        // Assert
        assertNotNull(result);
        assertEquals(outputModel.getUsername(), result.getUsername());
        assertEquals(outputModel.getAddress(), result.getAddress());
        assertEquals(outputModel.getEmail(), result.getEmail());
    }

    

    
}
