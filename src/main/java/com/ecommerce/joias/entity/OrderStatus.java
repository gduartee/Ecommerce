package com.ecommerce.joias.entity;

public enum OrderStatus {
    WAITING_PAYMENT, // Aguardando Pagamento
    PAID,            // Pago
    SHIPPED,         // Enviado
    DELIVERED,       // Entregue
    CANCELED         // Cancelado
}
