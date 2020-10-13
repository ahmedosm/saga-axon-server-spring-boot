package com.osmanco.ordermanagement.orderservice.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.osmanco.ordermanagement.orderservice.aggregates.OrderStatus;
import com.osmanco.ordermanagement.orderservice.dto.OrderCreateDTO;
import com.progressivecoder.ecommerce.commands.CreateOrderCommand;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {
	private final CommandGateway commandGateway;

	public OrderCommandServiceImpl(CommandGateway commandGateway) {

		this.commandGateway = commandGateway;
	}

	public CompletableFuture<String> createOrder(OrderCreateDTO orderCreateDTO) {
		return commandGateway.send(new CreateOrderCommand(UUID.randomUUID().toString(), orderCreateDTO.getItemType(),
				orderCreateDTO.getPrice(), orderCreateDTO.getCurrency(), String.valueOf(OrderStatus.CREATED)));
	}

}
