package com.krnelx.ddd.aggregates;

import com.krnelx.ddd.entities.Order;
import com.krnelx.ddd.valueobjects.Address;
import com.krnelx.ddd.valueobjects.Email;
import com.krnelx.ddd.valueobjects.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerAggregate {

    private final UUID id;
    private final Name name;
    private final Email email;
    private Address address;
    private final List<Order> orders;

    public CustomerAggregate(UUID id, Name name, Email email, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        if (order != null) {
            this.orders.add(order);
        }
    }

    public void updateAddress(Address newAddress) {
        if (newAddress != null) {
            this.address = newAddress;
        }
    }

    public boolean hasActiveOrders() {
        return orders.stream().anyMatch(order -> !order.getStatus().isCompleted());
    }

    public List<Order> getOrders() {
        return List.copyOf(orders);
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public UUID getId() {
        return id;
    }
}
