package com.krnelx.ddd.entities;

import com.k1fl1k.ddd.valueobjects.*;
import com.krnelx.ddd.valueobjects.Money;
import com.krnelx.ddd.valueobjects.ProductDetails;
import com.krnelx.ddd.valueobjects.Stock;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Product {

    private final UUID id;
    private final ProductDetails details;
    private Money price;
    private Stock stock;

    public Product(UUID id, ProductDetails details, Money price, Stock stock) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.details = Objects.requireNonNull(details, "Details cannot be null");
        this.price = Objects.requireNonNull(price, "Price cannot be null");
        this.stock = Objects.requireNonNull(stock, "Stock cannot be null");
    }

    public UUID getId() {
        return id;
    }

    public ProductDetails getDetails() {
        return details;
    }

    public Money getPrice() {
        return price;
    }

    public Stock getStock() {
        return stock;
    }

    public void updatePrice(Money newPrice) {
        if (newPrice.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.price = newPrice;
    }

    public void reduceStock(int quantity) {
        stock = stock.reduceStock(quantity);
    }

    public boolean hasSufficientStock(int quantity) {
        return stock.getQuantity() >= quantity;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", details=" + details + ", price=" + price + ", stock=" + stock + '}';
    }
}
