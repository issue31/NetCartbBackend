
package com.ecom.netcart.controllertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecom.netcart.controller.ProductController;
import com.ecom.netcart.exception.ProductNotFoundException;
import com.ecom.netcart.model.ProductOutputModel;
import com.ecom.netcart.service.ProductServiceImpl;

@ExtendWith(SpringExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @Test
    public void testGetProductByName() throws Exception {
        // Arrange
        String productName = "ExampleProduct";
        ProductOutputModel productOutputModel = new ProductOutputModel();
        productOutputModel.setPId(1);
        productOutputModel.setPName(productName);
        productOutputModel.setPPrice(10);

        when(productService.getProductByName(productName)).thenReturn(productOutputModel);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        // Act & Assert
        mockMvc.perform(get("/findproduct/{name}", productName))
            .andExpect(status().isOk());
        // Add more assertions based on the expected response
    }

    @Test
    public void testGetProductByName_ProductNotFound() throws Exception {
        // Arrange
        String productName = "InvalidProduct";

        when(productService.getProductByName(productName)).thenThrow(ProductNotFoundException.class);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        // Act & Assert
        mockMvc.perform(get("/findproduct/{name}", productName))
            .andExpect(status().isNotFound());
        // Add more assertions based on the expected response
    }

    @Test
    public void testGetProductById() throws Exception {
        // Arrange
        int productId = 1;
        ProductOutputModel productOutputModel = new ProductOutputModel();
        productOutputModel.setPId(productId);
        productOutputModel.setPName("ExampleProduct");
        productOutputModel.setPPrice(10);

        when(productService.getProductById(productId)).thenReturn(productOutputModel);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        // Act & Assert
        mockMvc.perform(get("/findbyid").param("pId", String.valueOf(productId)))
            .andExpect(status().isOk());
        // Add more assertions based on the expected response
    }

    @Test
    public void testGetProductById_ProductNotFound() throws Exception {
        // Arrange
        int productId = 999;

        when(productService.getProductById(productId)).thenThrow(ProductNotFoundException.class);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        // Act & Assert
        mockMvc.perform(get("/findbyid").param("pId", String.valueOf(productId)))
            .andExpect(status().isNotFound());
        // Add more assertions based on the expected response
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // Arrange
        List<ProductOutputModel> productOutputModels = new ArrayList<>();
        ProductOutputModel product1 = new ProductOutputModel();
        product1.setPId(1);
        product1.setPName("Product1");
        product1.setPPrice(10);
        productOutputModels.add(product1);
        // Addmore products as needed

        when(productService.getAllProducts()).thenReturn(productOutputModels);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        // Act & Assert
        mockMvc.perform(get("/getallproduct"))
            .andExpect(status().isOk());
        // Add more assertions based on the expected response
    }
}
