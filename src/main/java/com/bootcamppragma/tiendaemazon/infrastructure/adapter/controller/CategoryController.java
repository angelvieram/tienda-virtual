package com.bootcamppragma.tiendaemazon.infrastructure.adapter.controller;

import com.bootcamppragma.tiendaemazon.application.port.in.CreateCategoryUseCase;
import com.bootcamppragma.tiendaemazon.domain.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias")
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;

    public CategoryController(CreateCategoryUseCase createCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category categoryRequest) {
        Category category = createCategoryUseCase.createCategory(
                categoryRequest.getId(),
                categoryRequest.getName(),
                categoryRequest.getDescription()
        );
        return ResponseEntity.ok(category);
    }
}
