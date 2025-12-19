package com.ecommerce.joias.controller;

import com.ecommerce.joias.dto.create.CreateOrderDto;
import com.ecommerce.joias.dto.response.OrderResponseDto;
import com.ecommerce.joias.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/user/{userId}/address/{addressId}")
    public ResponseEntity<OrderResponseDto> createOrder(@PathVariable("userId") UUID userId, @PathVariable("addressId") Integer addressId, @RequestBody CreateOrderDto createOrderDto){
        var orderDto = orderService.createOrder(userId, addressId, createOrderDto);

        URI location = URI.create("/orders/" + orderDto.orderId());

        return ResponseEntity.created(location).body(orderDto);
    }
}
