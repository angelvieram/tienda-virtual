package com.bootcamppragma.tiendaemazon.application.port.in;

import com.bootcamppragma.tiendaemazon.application.port.out.SaveCategoryPort;
import com.bootcamppragma.tiendaemazon.domain.exception.CategoryAlreadyExistsException;
import com.bootcamppragma.tiendaemazon.domain.exception.InvalidCategoryNameException;
import com.bootcamppragma.tiendaemazon.domain.model.Category;

public class CreateCategoryUseCase {
    private final SaveCategoryPort saveCategoryPort;

    public CreateCategoryUseCase(SaveCategoryPort saveCategoryPort) {
        this.saveCategoryPort = saveCategoryPort;
    }

    public Category createCategory(Long id, String name, String description) {
        // Validar el nombre de la categoría
        if (name == null || name.isBlank() || name.length() > 50) {
            throw new InvalidCategoryNameException("El nombre de la categoría no es válido");
        }
        // Validar la descripción de la categoría
        if (description == null || description.length() > 90) {
            throw new IllegalArgumentException("La descripción no es válida");
        }

        // Crear la categoría
        Category category = new Category(id, name, description);

        // Guardar la categoría utilizando el puerto de salida
        try {
            return saveCategoryPort.saveCategory(category);
        } catch (CategoryAlreadyExistsException e) {
            throw new CategoryAlreadyExistsException("La categoría con este ID ya existe.");
        }
    }
}



