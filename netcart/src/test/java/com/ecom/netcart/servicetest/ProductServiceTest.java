package com.ecom.netcart.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ecom.netcart.entity.Product;
import com.ecom.netcart.exception.ProductNotFoundException;
import com.ecom.netcart.model.ProductOutputModel;
import com.ecom.netcart.repository.ProductDao;
import com.ecom.netcart.service.ProductServiceImpl;

public class ProductServiceTest {
	
    @Mock
    private ProductDao productDao;
    
    @InjectMocks
    private ProductServiceImpl productService;
   
    @BeforeEach
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductByName_ProductFound() throws ProductNotFoundException {
        // Arrange
        String productName = "Sample Product";
        Product sampleProduct = new Product();
        sampleProduct.setPId(1);
        sampleProduct.setPName(productName);
        sampleProduct.setPDesc("Sample Description");
        sampleProduct.setPPrice(100);

        when(productDao.findByName(productName)).thenReturn(sampleProduct);

        // Act
        ProductOutputModel result = productService.getProductByName(productName);

        // Assert
        assertNotNull(result);
        assertEquals(sampleProduct.getPId(), result.getPId());
        assertEquals(sampleProduct.getPName(), result.getPName());
        assertEquals(sampleProduct.getPDesc(), result.getPDesc());
        assertEquals(sampleProduct.getPPrice(), result.getPPrice());
    }

    @Test
    public void testGetProductByName_ProductNotFound() {
        // Arrange
        String productName = "Non-existent Product";

        when(productDao.findByName(productName)).thenReturn(null);

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductByName(productName);
        });
    }
    @Test
    public void testGetProductById_ProductFound() throws ProductNotFoundException {
        // Arrange
        int productId = 1;
        Product sampleProduct = new Product();
        sampleProduct.setPId(productId);
        sampleProduct.setPName("Sample Product");
        sampleProduct.setPDesc("Sample Description");
        sampleProduct.setPPrice(100);

        when(productDao.findById(productId)).thenReturn(Optional.of(sampleProduct));

        // Act
        ProductOutputModel result = productService.getProductById(productId);

        // Assert
        assertNotNull(result);
        assertEquals(sampleProduct.getPId(), result.getPId());
        assertEquals(sampleProduct.getPName(), result.getPName());
        assertEquals(sampleProduct.getPDesc(), result.getPDesc());
        assertEquals(sampleProduct.getPPrice(), result.getPPrice());
    }

    @Test
    public void testGetProductById_ProductNotFound() {
        // Arrange
        int productId = 999; // A non-existent product ID

        when(productDao.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(productId);
        });
    }
    
    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> sampleProducts = new ArrayList<>();
        Product product1 = new Product();
        product1.setPId(1);
        product1.setPName("Product 1");
        product1.setPDesc("Description 1");
        product1.setPPrice(100);
        sampleProducts.add(product1);

        Product product2 = new Product();
        product2.setPId(2);
        product2.setPName("Product 2");
        product2.setPDesc("Description 2");
        product2.setPPrice(200);
        sampleProducts.add(product2);

        when(productDao.findAll()).thenReturn(sampleProducts);

        // Act
        List<ProductOutputModel> result = productService.getAllProducts();

        // Assert
        assertNotNull(result);
        assertEquals(sampleProducts.size(), result.size());

        for (int i = 0; i < sampleProducts.size(); i++) {
            Product sampleProduct = sampleProducts.get(i);
            ProductOutputModel outputModel = result.get(i);

            assertEquals(sampleProduct.getPId(), outputModel.getPId());
            assertEquals(sampleProduct.getPName(), outputModel.getPName());
            assertEquals(sampleProduct.getPDesc(), outputModel.getPDesc());
            assertEquals(sampleProduct.getPPrice(), outputModel.getPPrice());
            // Add more assertions if you have additional fields in your ProductOutputModel
        }
    }
}








