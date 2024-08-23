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
        CategoryJpaEntity entity = new CategoryJpaEntity();
        entity.setName(category.getName());
        entity.setDescription(category.getDescription());
        CategoryJpaEntity savedEntity = categoryJpaRepository.save(entity);
        return new Category(savedEntity.getId(), savedEntity.getName(), savedEntity.getDescription());
    }
}
