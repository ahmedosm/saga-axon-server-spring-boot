package com.osmanco.ordermanagement.orderservice.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderCreateDTO {

	private String itemType;

	private BigDecimal price;

	private String currency;

}
