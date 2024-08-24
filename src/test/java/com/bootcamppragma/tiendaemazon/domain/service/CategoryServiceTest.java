package com.bootcamppragma.tiendaemazon.domain.service;

import com.bootcamppragma.tiendaemazon.domain.model.Category;
import com.bootcamppragma.tiendaemazon.infrastructure.adapter.persistence.CategoryJpaEntity;
import com.bootcamppragma.tiendaemazon.infrastructure.adapter.persistence.CategoryJpaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {

    @Test
    void createCategory_CategoryDoesNotExist_CreatesAndReturnsCategory() {
        // Arrange
        CategoryJpaRepository repository = Mockito.mock(CategoryJpaRepository.class);
        CategoryService service = new CategoryService(repository);

        CategoryJpaEntity entity = new CategoryJpaEntity(1L, "Category1", "Description");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(repository.save(Mockito.any(CategoryJpaEntity.class))).thenReturn(entity);

        // Act
        Category result = service.createCategory(1L, "Category1", "Description");

        // Assert
        assertNotNull(result);
        assertEquals("Category1", result.getName());
        assertEquals("Description", result.getDescription());
    }

    @Test
    void createCategory_CategoryExists_ThrowsException() {
        // Arrange
        CategoryJpaRepository repository = Mockito.mock(CategoryJpaRepository.class);
        CategoryService service = new CategoryService(repository);

        CategoryJpaEntity existingEntity = new CategoryJpaEntity(1L, "Category1", "Description");
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(existingEntity));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.createCategory(1L, "Category1", "Description");
        });
    }
}

