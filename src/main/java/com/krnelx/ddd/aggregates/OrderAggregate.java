package com.krnelx.ddd.aggregates;

import com.krnelx.ddd.valueobjects.Address;
import com.krnelx.ddd.valueobjects.Money;
import com.krnelx.ddd.valueobjects.OrderItemDetails;
import com.krnelx.ddd.valueobjects.OrderStatus;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderAggregate {

    private final UUID id;
    private Address shippingAddress;
    private Money totalPrice;
    private OrderStatus status;
    private final List<OrderItemDetails> orderItems;

    public OrderAggregate(UUID id, Address shippingAddress) {
        this.id = id;
        this.shippingAddress = shippingAddress;
        this.totalPrice = new Money("USD", BigDecimal.ZERO);
        this.status = OrderStatus.NEW;
        this.orderItems = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItemDetails> getOrderItems() {
        return orderItems;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void addOrderItem(OrderItemDetails item) {
        orderItems.add(item);
        recalculateTotalPrice();
    }

    private void recalculateTotalPrice() {
        BigDecimal total = orderItems.stream()
            .map(item -> item.getPrice().getAmount().multiply(new BigDecimal(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        totalPrice = new Money("USD", total);
    }

    public void updateShippingAddress(Address newAddress) {
        this.shippingAddress = newAddress;
    }

    public void changeStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }
}
