package com.krnelx.ddd.valueobjects;

import com.krnelx.ddd.exceptions.InvalidOrderItemException;
import java.util.Objects;
import java.util.UUID;

public final class OrderItemDetails {

    private final UUID productId;
    private final int quantity;
    private final Money price;

    public OrderItemDetails(UUID productId, int quantity, Money price) {
        if (productId == null) {
            throw new InvalidOrderItemException("Product ID cannot be null.");
        }
        if (quantity < 1) {
            throw new InvalidOrderItemException("Quantity must be at least 1.");
        }
        if (price == null) {
            throw new InvalidOrderItemException("Price cannot be null.");
        }
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public Money getTotalPrice() {
        return new Money(price.getCurrency(), price.getAmount().multiply(
            java.math.BigDecimal.valueOf(quantity)));
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemDetails that)) return false;
        return quantity == that.quantity &&
            productId.equals(that.productId) &&
            price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity, price);
    }

    @Override
    public String toString() {
        return "OrderItemDetails{" +
            "productId=" + productId +
            ", quantity=" + quantity +
            ", price=" + price +
            '}';
    }
}
