package com.bootcamppragma.tiendaemazon.application.port.out;

import com.bootcamppragma.tiendaemazon.domain.model.Category;

public interface SaveCategoryPort {
    Category saveCategory(Category category);
}
