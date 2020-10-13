package com.osmanco.ordermanagement.orderservice.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osmanco.ordermanagement.orderservice.dto.OrderCreateDTO;
import com.osmanco.ordermanagement.orderservice.service.OrderCommandService;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderCommandController {

	private OrderCommandService orderCommandService;

	public OrderCommandController(OrderCommandService orderCommandService) {
		this.orderCommandService = orderCommandService;
	}

	@PostMapping
	public CompletableFuture<String> createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
		return orderCommandService.createOrder(orderCreateDTO);
	}

}