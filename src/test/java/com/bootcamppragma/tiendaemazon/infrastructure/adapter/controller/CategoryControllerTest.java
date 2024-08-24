package com.bootcamppragma.tiendaemazon.infrastructure.adapter.controller;

import com.bootcamppragma.tiendaemazon.application.port.in.CreateCategoryUseCase;
import com.bootcamppragma.tiendaemazon.domain.model.Category;
import com.bootcamppragma.tiendaemazon.domain.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createCategory_ValidRequest_ReturnsCategory() throws Exception {
        // Arrange
        CreateCategoryUseCase useCase = Mockito.mock(CreateCategoryUseCase.class);
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        CategoryController controller = new CategoryController(useCase, categoryService);

        Category category = new Category(1L, "Category1", "Description");
        Mockito.when(useCase.createCategory(1L, "Category1", "Description")).thenReturn(category);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Category1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Description"));
    }

    @Test
    void getAllCategories_ValidRequest_ReturnsPagedCategories() throws Exception {
        // Arrange
        CategoryService categoryService = Mockito.mock(CategoryService.class);
        CategoryController controller = new CategoryController(Mockito.mock(CreateCategoryUseCase.class), categoryService);

        Category category = new Category(1L, "Category1", "Description");
        Mockito.when(categoryService.getAllCategories(0, 10, "asc", "name"))
                .thenReturn(Collections.singletonList(category));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/categorias")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sortDirection", "asc")
                        .param("sortBy", "name")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Category1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("Description"));
    }
}

