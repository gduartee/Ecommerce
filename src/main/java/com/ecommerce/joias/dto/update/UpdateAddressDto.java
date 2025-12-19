package com.ecommerce.joias.dto.update;

import jakarta.validation.constraints.NotBlank;

public record UpdateAddressDto(
        String cep,
        String street,
        String num
) {
}
