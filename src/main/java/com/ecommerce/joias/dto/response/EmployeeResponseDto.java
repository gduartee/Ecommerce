package com.ecommerce.joias.dto.response;

import java.util.List;

public record EmployeeResponseDto(
        Integer employeeId,
        String name,
        String email,
        String password,
        String role,
        List<ImageResponseDto> images
) {
}
