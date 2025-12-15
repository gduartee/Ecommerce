package com.ecommerce.joias.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDto(
        @NotBlank(message = "Nome da categoria obrigatório")
        String name
) {
}
