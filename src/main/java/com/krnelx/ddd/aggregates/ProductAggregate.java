package com.krnelx.ddd.aggregates;

import com.krnelx.ddd.valueobjects.Money;
import com.krnelx.ddd.valueobjects.ProductDetails;
import com.krnelx.ddd.valueobjects.Stock;
import java.util.UUID;

public class ProductAggregate {

    private final UUID id;
    private final ProductDetails details;
    private Money price;
    private Stock stock;

    public ProductAggregate(UUID id, ProductDetails details, Money price, Stock stock) {
        this.id = id;
        this.details = details;
        this.price = price;
        this.stock = stock;
    }

    public UUID getId() {
        return id;
    }

    public boolean reduceStock(int quantity) {
        if (stock.getQuantity() >= quantity) {
            stock = stock.reduceStock(quantity);
            return true;
        }
        return false;
    }

    public void updatePrice(Money newPrice) {
        this.price = newPrice;
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
}
