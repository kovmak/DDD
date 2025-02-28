package com.krnelx.ddd.entities;

import com.k1fl1k.ddd.valueobjects.*;
import com.krnelx.ddd.valueobjects.Address;
import com.krnelx.ddd.valueobjects.Money;
import com.krnelx.ddd.valueobjects.OrderItemDetails;
import com.krnelx.ddd.valueobjects.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {

    private final UUID id;
    private final Customer customer;
    private final List<OrderItemDetails> items;
    private Money totalPrice;
    private OrderStatus status;
    private Address shippingAddress;

    public Order(UUID id, Customer customer, Address shippingAddress) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.customer = Objects.requireNonNull(customer, "Customer cannot be null");
        this.items = new ArrayList<>();
        this.totalPrice = new Money("USD", BigDecimal.ZERO);
        this.status = OrderStatus.NEW;
        this.shippingAddress = Objects.requireNonNull(shippingAddress, "Shipping address cannot be null");
    }

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItemDetails> getItems() {
        return List.copyOf(items);
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void addItem(OrderItemDetails item) {
        items.add(Objects.requireNonNull(item, "Item cannot be null"));
        totalPrice = totalPrice.add(item.getTotalPrice());
    }

    public void changeStatus(OrderStatus newStatus) {
        if (status == OrderStatus.SHIPPED && newStatus != OrderStatus.DELIVERED) {
            throw new IllegalStateException("Cannot revert from SHIPPED status.");
        }
        this.status = newStatus;
    }

    public void changeShippingAddress(Address newAddress) {
        if (status == OrderStatus.SHIPPED || status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Cannot change address after shipping.");
        }
        this.shippingAddress = Objects.requireNonNull(newAddress, "New address cannot be null");
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", customer=" + customer + ", totalPrice=" + totalPrice + ", status=" + status + '}';
    }
}
