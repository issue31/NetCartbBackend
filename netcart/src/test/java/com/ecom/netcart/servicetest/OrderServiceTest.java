package com.ecom.netcart.servicetest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ecom.netcart.entity.Orders;
import com.ecom.netcart.entity.Product;
import com.ecom.netcart.entity.User;
import com.ecom.netcart.exception.OrderNotFoundException;
import com.ecom.netcart.exception.UserNotFoundException;
import com.ecom.netcart.model.OrderInputModel;
import com.ecom.netcart.model.OrderOutputModel;
import com.ecom.netcart.repository.OrderDao;
import com.ecom.netcart.repository.ProductDao;
import com.ecom.netcart.repository.UserDao;
import com.ecom.netcart.service.OrderServiceImpl;
@ExtendWith(SpringExtension.class)
public class OrderServiceTest {
	@Mock
    private OrderDao orderDao;
	@Mock
    private UserDao userDao;
	@Mock
    private ProductDao productDao;
    @InjectMocks
    private OrderServiceImpl orderService;

    
    @Test
    public void testMakeOrder() throws UserNotFoundException {
        // Arrange
        User user = new User();
        user.setUserId(1);
        when(userDao.findById(1)).thenReturn(Optional.of(user));

        Product product1 = new Product();
        product1.setPId(1);
        product1.setPPrice(10);

        Product product2 = new Product();
        product2.setPId(2);
        product2.setPPrice(20);

        when(productDao.findById(1)).thenReturn(Optional.of(product1));
        when(productDao.findById(2)).thenReturn(Optional.of(product2));

        List<OrderInputModel> inputModels = new ArrayList<>();
        OrderInputModel inputModel1 = new OrderInputModel();
        inputModel1.setPId(1);
        OrderInputModel inputModel2 = new OrderInputModel();
        inputModel2.setPId(2);
        inputModels.add(inputModel1);
        inputModels.add(inputModel2);

        // Act
        List<OrderInputModel> result = orderService.makeOrder(inputModels, 1);

        // Assert
        assertEquals(2, result.size());
        // Add more assertions based on your requirements
    }

    
    @Test
    public void testCancelOrder() throws OrderNotFoundException {
        // Arrange
        Orders order = new Orders();
        order.setOrderId(1);
        order.setStatus("Booked");
        
        User user = new User();
        user.setUserId(1);
        order.setUser(user);

        when(orderDao.findById(1)).thenReturn(Optional.of(order));

        // Act
        OrderOutputModel result = orderService.cancelOrder(1);

        // Assert
        assertEquals("Cancelled", result.getStatus());
        // Add more assertions based on your requirements
    }


    

    @Test
    public void testGetOrderByUserId() throws UserNotFoundException {
        // Arrange
        User user = new User();
        user.setUserId(1);
        when(userDao.findById(1)).thenReturn(Optional.of(user));

        Orders order1 = new Orders();
        order1.setOrderId(1);
        order1.setStatus("Booked");
        order1.setOrderDate(LocalDate.now());
        order1.setPrice(10);
        order1.setUser(user);

        Orders order2 = new Orders();
        order2.setOrderId(2);
        order2.setStatus("Booked");
        order2.setOrderDate(LocalDate.now());
        order2.setPrice(20);
        order2.setUser(user);

        List<Orders> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        when(orderDao.findByUserUserId(1)).thenReturn(orders);

        // Act
        List<OrderOutputModel> result = orderService.getOrderByUserId(1);

        // Assert
        assertEquals(2, result.size());
        // Add more assertions based on your requirements
    }

   
}
