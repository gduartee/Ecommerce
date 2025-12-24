package com.ecommerce.joias.dto.response;

import java.util.List;

public record ApiResponse<T>(
        List<T> data,
        long totalElements, // Total de itens no banco todo
        int totalPages,     // Total de páginas disponíveis
        int page,           // Página atual (começa em 0)
        int size            // Quantos itens vieram nessa página (limit)
) {
}