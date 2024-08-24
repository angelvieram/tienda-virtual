package com.bootcamppragma.tiendaemazon.domain.repository;

import com.bootcamppragma.tiendaemazon.domain.model.Category;

import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findById(Long id);

    Category save(Category category);

    void deleteById(Long id);
}

