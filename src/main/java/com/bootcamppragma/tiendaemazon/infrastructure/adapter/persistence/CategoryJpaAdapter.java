package com.bootcamppragma.tiendaemazon.infrastructure.adapter.persistence;

import com.bootcamppragma.tiendaemazon.application.port.out.SaveCategoryPort;
import com.bootcamppragma.tiendaemazon.domain.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryJpaAdapter implements SaveCategoryPort {
    private final CategoryJpaRepository categoryJpaRepository;

    public CategoryJpaAdapter(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        // Convertir el objeto de dominio a entidad JPA
        CategoryJpaEntity entity = new CategoryJpaEntity();
        entity.setName(category.getName());
        entity.setDescription(category.getDescription());

        // Guardar la entidad en la base de datos
        CategoryJpaEntity savedEntity = categoryJpaRepository.save(entity);

        // Convertir la entidad JPA guardada de vuelta a un objeto de dominio
        return new Category(savedEntity.getId(), savedEntity.getName(), savedEntity.getDescription());
    }
}

