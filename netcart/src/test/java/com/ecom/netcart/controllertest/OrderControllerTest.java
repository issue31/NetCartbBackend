
package com.ecom.netcart.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecom.netcart.controller.OrderController;
import com.ecom.netcart.exception.OrderNotFoundException;
import com.ecom.netcart.exception.UserNotFoundException;
import com.ecom.netcart.model.OrderInputModel;
import com.ecom.netcart.model.OrderOutputModel;
import com.ecom.netcart.service.OrderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderServiceImpl orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    @Test
    public void testMakeOrder() throws Exception {
        // Arrange
        List<OrderInputModel> inputModels = new ArrayList<>();
        OrderInputModel inputModel1 = new OrderInputModel();
        inputModel1.setPId(1);
        OrderInputModel inputModel2 = new OrderInputModel();
        inputModel2.setPId(2);
        inputModels.add(inputModel1);
        inputModels.add(inputModel2);

        when(orderService.makeOrder(any(List.class), any(Integer.class))).thenReturn(inputModels);

        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        // Act & Assert
        mockMvc.perform(post("/makeorder/{userId}", 1)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(inputModels)))
            .andExpect(status().isOk());
        // Add more assertions based on the expected response
    }

    @Test
    public void testCancelOrder() throws Exception {
        // Arrange
        int orderId = 1;
        OrderOutputModel orderOutputModel = new OrderOutputModel();
        orderOutputModel.setOrderId(orderId);

        when(orderService.cancelOrder(orderId)).thenReturn(orderOutputModel);

        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        // Act & Assert
        mockMvc.perform(put("/cancelOrder/{orderId}", orderId))
            .andExpect(status().isOk());
        // Add more assertions based on the expected response
    }

    @Test
    public void testGetOrderByUserId() throws Exception {
        // Arrange
        int userId = 1;
        List<OrderOutputModel> orderOutputModels = new ArrayList<>();
        OrderOutputModel order1 = new OrderOutputModel();
        order1.setOrderId(1);
        orderOutputModels.add(order1);
        // Add more orders as needed

        when(orderService.getOrderByUserId(userId)).thenReturn(orderOutputModels);

        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        // Act & Assert
        mockMvc.perform(get("/getAllOrders/{userId}", userId))
            .andExpect(status().isOk());
        // Add more assertions based on the expected response
    }
}
