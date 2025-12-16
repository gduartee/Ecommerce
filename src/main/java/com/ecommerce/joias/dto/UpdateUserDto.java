package com.ecommerce.joias.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserDto(
        String name,
        String email,
        String phoneNumber
) {
}
