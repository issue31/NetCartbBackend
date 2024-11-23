package com.ecom.netcart.servicetest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ecom.netcart.entity.Category;
import com.ecom.netcart.exception.CategoryNotFoundException;
import com.ecom.netcart.model.CategoryOutputModel;
import com.ecom.netcart.repository.CategoryDao;
import com.ecom.netcart.service.CategoryServiceImpl;
@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryDao categoryDao;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCategoryByName_CategoryFound() throws CategoryNotFoundException {
        // Arrange
        String categoryName = "Electronics";
        Category category = new Category();
        category.setCategoryTitle(categoryName);
        category.setCategoryDescription("Electronics category description");

        when(categoryDao.findByName(categoryName)).thenReturn(category);

        // Act
        CategoryOutputModel result = categoryService.getCategoryByName(categoryName);

        // Assert
        assertNotNull(result);
        assertEquals(category.getCategoryTitle(), result.getCategoryTitle());
        assertEquals(category.getCategoryDescription(), result.getCategoryDescription());
    }

    @Test
    public void testGetCategoryByName_CategoryNotFound() {
        // Arrange
        String categoryName = "NonExistentCategory";
        when(categoryDao.findByName(categoryName)).thenReturn(null);

        // Act & Assert
        assertThrows(CategoryNotFoundException.class, () -> categoryService.getCategoryByName(categoryName));
    }

    @Test
    public void testGetAllCategories() {
        // Arrange
        List<Category> categories = new ArrayList<>();
        Category category1 = new Category();
        category1.setCategoryTitle("Electronics");
        category1.setCategoryDescription("Electronics category description");

        Category category2 = new Category();
        category2.setCategoryTitle("Clothing");
        category2.setCategoryDescription("Clothing category description");

        categories.add(category1);
        categories.add(category2);

        when(categoryDao.findAll()).thenReturn(categories);

        // Act
        List<CategoryOutputModel> result = categoryService.getAllCategories();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(category1.getCategoryTitle(), result.get(0).getCategoryTitle());
        assertEquals(category1.getCategoryDescription(), result.get(0).getCategoryDescription());
        assertEquals(category2.getCategoryTitle(), result.get(1).getCategoryTitle());
        assertEquals(category2.getCategoryDescription(), result.get(1).getCategoryDescription());
    }
}
