package com.bootcamppragma.tiendaemazon.infrastructure.configuration;

import com.bootcamppragma.tiendaemazon.application.port.in.CreateCategoryUseCase;
import com.bootcamppragma.tiendaemazon.application.port.out.SaveCategoryPort;
import com.bootcamppragma.tiendaemazon.domain.service.CategoryService;
import com.bootcamppragma.tiendaemazon.infrastructure.adapter.persistence.CategoryJpaAdapter;
import com.bootcamppragma.tiendaemazon.infrastructure.adapter.persistence.CategoryJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(SaveCategoryPort saveCategoryPort) {
        return new CreateCategoryUseCase(saveCategoryPort);
    }

    @Bean
    public SaveCategoryPort saveCategoryPort(CategoryJpaRepository categoryJpaRepository) {
        return new CategoryJpaAdapter(categoryJpaRepository);
    }

    @Bean
    public CategoryService categoryService(CategoryJpaRepository categoryJpaRepository) {
        return new CategoryService(categoryJpaRepository);
    }
}

