package com.bootcamppragma.tiendaemazon.infrastructure.adapter.controller.dto;

import lombok.Data;

@Data
public class CategoryRequestDto {
    private Long id;
    private String name;
    private String description;
}

