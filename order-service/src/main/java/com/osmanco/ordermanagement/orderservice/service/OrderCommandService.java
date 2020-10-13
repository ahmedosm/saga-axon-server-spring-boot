package com.osmanco.ordermanagement.orderservice.service;

import java.util.concurrent.CompletableFuture;

import com.osmanco.ordermanagement.orderservice.dto.OrderCreateDTO;

public interface OrderCommandService {

    public CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO);

}