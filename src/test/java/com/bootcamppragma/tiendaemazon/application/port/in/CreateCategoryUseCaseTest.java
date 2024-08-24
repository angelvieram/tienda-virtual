package com.bootcamppragma.tiendaemazon.application.port.in;

import com.bootcamppragma.tiendaemazon.application.port.out.SaveCategoryPort;
import com.bootcamppragma.tiendaemazon.domain.exception.InvalidCategoryNameException;
import com.bootcamppragma.tiendaemazon.domain.model.Category;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CreateCategoryUseCaseTest {

    @Test
    void createCategory_ValidData_ReturnsCategory() {
        // Arrange
        SaveCategoryPort saveCategoryPort = Mockito.mock(SaveCategoryPort.class);
        CreateCategoryUseCase useCase = new CreateCategoryUseCase(saveCategoryPort);

        Category category = new Category(1L, "Category1", "Description");
        Mockito.when(saveCategoryPort.saveCategory(Mockito.any(Category.class)))
                .thenReturn(category);

        // Act
        Category result = useCase.createCategory(1L, "Category1", "Description");

        // Assert
        assertNotNull(result);
        assertEquals("Category1", result.getName());
        assertEquals("Description", result.getDescription());
    }

    @Test
    void createCategory_InvalidName_ThrowsException() {
        // Arrange
        SaveCategoryPort saveCategoryPort = Mockito.mock(SaveCategoryPort.class);
        CreateCategoryUseCase useCase = new CreateCategoryUseCase(saveCategoryPort);

        // Act & Assert
        assertThrows(InvalidCategoryNameException.class, () -> {
            useCase.createCategory(1L, "", "Description");
        });
    }
}

