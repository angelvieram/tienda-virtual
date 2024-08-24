package com.bootcamppragma.tiendaemazon.infrastructure.adapter.controller;

import com.bootcamppragma.tiendaemazon.application.port.in.CreateCategoryUseCase;
import com.bootcamppragma.tiendaemazon.domain.model.Category;
import com.bootcamppragma.tiendaemazon.domain.service.CategoryService;
import com.bootcamppragma.tiendaemazon.infrastructure.adapter.controller.dto.CategoryRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final CategoryService categoryService;

    public CategoryController(CreateCategoryUseCase createCategoryUseCase, CategoryService categoryService) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        // Convertir CategoryRequestDto a Category
        Category category = createCategoryUseCase.createCategory(
                categoryRequestDto.getId(),
                categoryRequestDto.getName(),
                categoryRequestDto.getDescription()
        );
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "name") String sortBy) {
        List<Category> categories = categoryService.getAllCategories(page, size, sortDirection, sortBy);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
