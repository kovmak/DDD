package com.krnelx.ddd.entities;

import com.k1fl1k.ddd.valueobjects.*;
import com.krnelx.ddd.valueobjects.Address;
import com.krnelx.ddd.valueobjects.Email;
import com.krnelx.ddd.valueobjects.Name;
import com.krnelx.ddd.valueobjects.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Customer {

    private final UUID id;
    private final Name name;
    private final Email email;
    private Address address;
    private final List<Order> orders;

    public Customer(UUID id, Name name, Email email, Address address) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
        this.address = Objects.requireNonNull(address, "Address cannot be null");
        this.orders = new ArrayList<>();
    }

    public UUID getId() {
        return id;
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

    public List<Order> getOrders() {
        return List.copyOf(orders);
    }

    public void changeAddress(Address newAddress) {
        this.address = Objects.requireNonNull(newAddress, "New address cannot be null");
    }

    public void addOrder(Order order) {
        orders.add(Objects.requireNonNull(order, "Order cannot be null"));
    }

    public boolean hasActiveOrders() {
        return orders.stream().anyMatch(order -> order.getStatus() != OrderStatus.DELIVERED);
    }

    @Override
    public String toString() {
        return "Customer{id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + '}';
    }
}
