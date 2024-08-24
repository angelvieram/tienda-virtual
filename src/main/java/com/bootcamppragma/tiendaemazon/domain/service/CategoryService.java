package com.bootcamppragma.tiendaemazon.domain.service;

import com.bootcamppragma.tiendaemazon.domain.model.Category;
import com.bootcamppragma.tiendaemazon.infrastructure.adapter.persistence.CategoryJpaEntity;
import com.bootcamppragma.tiendaemazon.infrastructure.adapter.persistence.CategoryJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryJpaRepository categoryJpaRepository;

    public CategoryService(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    public Category createCategory(Long id, String name, String description) {
        // Verificar si la categoría ya existe
        Optional<CategoryJpaEntity> existingEntity = categoryJpaRepository.findById(id);
        if (existingEntity.isPresent()) {
            throw new IllegalArgumentException("La categoría con este ID ya existe");
        }

        // Crear y guardar nueva categoría
        Category newCategory = new Category(id, name, description);
        CategoryJpaEntity entity = new CategoryJpaEntity();
        entity.setId(newCategory.getId());
        entity.setName(newCategory.getName());
        entity.setDescription(newCategory.getDescription());

        CategoryJpaEntity savedEntity = categoryJpaRepository.save(entity);
        return new Category(savedEntity.getId(), savedEntity.getName(), savedEntity.getDescription());
    }

    public Optional<Category> getCategoryById(Long id) {
        Optional<CategoryJpaEntity> entity = categoryJpaRepository.findById(id);
        return entity.map(e -> new Category(e.getId(), e.getName(), e.getDescription()));
    }

    public List<Category> getAllCategories() {
        return categoryJpaRepository.findAll().stream()
                .map(e -> new Category(e.getId(), e.getName(), e.getDescription()))
                .collect(Collectors.toList());
    }
}



