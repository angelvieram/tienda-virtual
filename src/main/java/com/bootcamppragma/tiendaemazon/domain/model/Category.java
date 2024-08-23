package com.bootcamppragma.tiendaemazon.domain.model;

import com.bootcamppragma.tiendaemazon.domain.exception.InvalidNamException;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Category {
    private Long id;
    private String name;
    private String description;

    // Constructor sin validaciones
    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

