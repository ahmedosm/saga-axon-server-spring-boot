package com.osmanco.paymentmanagement.paymentservice.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.osmanco.ecommerce.commands.CreateInvoiceCommand;
import com.osmanco.ecommerce.events.InvoiceCreatedEvent;



@Aggregate
public class InvoiceAggregate {
	@AggregateIdentifier
	private String paymentId;
	private String orderId;
	private InvoiceStatus invoiceStatus;

	public InvoiceAggregate() {
	}

	@CommandHandler
	public InvoiceAggregate(CreateInvoiceCommand createInvoiceCommand) {
		AggregateLifecycle.apply(new InvoiceCreatedEvent(createInvoiceCommand.paymentId, createInvoiceCommand.orderId));

	}

	@EventSourcingHandler
	public void on(InvoiceCreatedEvent invoiceCreatedEvent) {
		this.paymentId = invoiceCreatedEvent.paymentId;
		this.orderId = invoiceCreatedEvent.orderId;
		this.invoiceStatus = InvoiceStatus.PAID;
	}
}
