package com.bootcamppragma.tiendaemazon.infrastructure.configuration;

import com.bootcamppragma.tiendaemazon.application.port.in.CreateCategoryUseCase;
import com.bootcamppragma.tiendaemazon.application.port.out.SaveCategoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(SaveCategoryPort saveCategoryPort) {
        return new CreateCategoryUseCase(saveCategoryPort);
    }
}
