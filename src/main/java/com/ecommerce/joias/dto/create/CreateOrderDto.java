package com.ecommerce.joias.dto.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record CreateOrderDto(
        @NotEmpty(message = "O pedido não pode estar vazio")
        @Valid
        List<OrderItemDto> items
) {
        public record OrderItemDto(
                @NotNull(message = "ID da variante obrigatório")
                Integer variantId,

                @NotNull(message = "Quantidade obrigatória")
                @Positive(message = "Quantidade deve ser maior que zero")
                Integer quantity
        ){}
}
