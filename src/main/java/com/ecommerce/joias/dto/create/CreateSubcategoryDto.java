package com.ecommerce.joias.dto.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSubcategoryDto(
        @NotBlank(message = "Nome da subcategoria obrigatório")
        String name,

        @NotNull(message = "O id da categoria é obrigatório")
        Integer categoryId
) {
}
