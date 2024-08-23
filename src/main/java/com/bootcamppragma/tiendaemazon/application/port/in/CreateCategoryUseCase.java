package com.bootcamppragma.tiendaemazon.application.port.in;

import com.bootcamppragma.tiendaemazon.application.port.out.SaveCategoryPort;
import com.bootcamppragma.tiendaemazon.domain.model.Category;
import com.bootcamppragma.tiendaemazon.domain.repository.CategoryRepository;

public class CreateCategoryUseCase {
    public Category createCategory(Long id, String name, String description) {
        if (name == null || name.isBlank() || name.length() > 50) {
            throw new InvalidNamException("El nombre de la categoría no es válido");
        }
        if (description == null || description.length() > 90) {
            throw new IllegalArgumentException("La descripción no es válida");
        }

        // Crear y devolver la categoría después de validaciones
        return new Category(id, name, description);
    }
}
