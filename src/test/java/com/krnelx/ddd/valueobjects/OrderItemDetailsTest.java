package com.krnelx.ddd.valueobjects;

import static org.junit.jupiter.api.Assertions.*;

import com.krnelx.ddd.exceptions.InvalidOrderItemException;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class OrderItemDetailsTest {

    @Test
    void shouldCreateValidOrderItemDetails() {
        UUID productId = UUID.randomUUID();
        Money price = new Money("USD", new BigDecimal("10.00"));
        OrderItemDetails item = new OrderItemDetails(productId, 2, price);
        assertEquals(productId, item.getProductId());
        assertEquals(2, item.getQuantity());
        assertEquals(price, item.getPrice());
    }

    @Test
    void shouldThrowExceptionForNegativeQuantity() {
        UUID productId = UUID.randomUUID();
        Money price = new Money("USD", new BigDecimal("10.00"));
        assertThrows(InvalidOrderItemException.class, () -> new OrderItemDetails(productId, -1, price));
    }

    @Test
    void shouldThrowExceptionForNullPrice() {
        UUID productId = UUID.randomUUID();
        assertThrows(InvalidOrderItemException.class, () -> new OrderItemDetails(productId, 2, null));
    }
}
