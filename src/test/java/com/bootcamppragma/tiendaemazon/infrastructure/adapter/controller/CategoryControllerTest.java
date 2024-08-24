package com.bootcamppragma.tiendaemazon.infrastructure.adapter.controller;

import com.bootcamppragma.tiendaemazon.application.port.in.CreateCategoryUseCase;
import com.bootcamppragma.tiendaemazon.domain.model.Category;
import com.bootcamppragma.tiendaemazon.domain.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateCategoryUseCase createCategoryUseCase;

    @MockBean
    private CategoryService categoryService;

    @Test
    void createCategory_ValidRequest_ReturnsCategory() throws Exception {
        // Arrange
        Category category = new Category(1L, "Category1", "Description");
        Mockito.when(createCategoryUseCase.createCategory(1L, "Category1", "Description")).thenReturn(category);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Category1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Description"));
    }
}
