package com.krnelx.ddd.aggregates;

import static org.junit.jupiter.api.Assertions.*;

import com.krnelx.ddd.valueobjects.Dimensions;
import com.krnelx.ddd.valueobjects.Money;
import com.krnelx.ddd.valueobjects.ProductDetails;
import com.krnelx.ddd.valueobjects.Stock;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class ProductAggregateTest {

    @Test
    void shouldCreateValidProductAggregate() {
        UUID productId = UUID.randomUUID();
        Dimensions dimensions = new Dimensions(10, 10, 10);
        ProductDetails productDetails = new ProductDetails("Product 1", "Description", dimensions);
        Money price = new Money("USD", new BigDecimal("100.00"));
        Stock stock = new Stock(10);

        ProductAggregate productAggregate = new ProductAggregate(productId, productDetails, price, stock);

        assertEquals(productId, productAggregate.getId());
        assertEquals(productDetails, productAggregate.getDetails());
        assertEquals(price, productAggregate.getPrice());
        assertEquals(stock, productAggregate.getStock());
    }

    @Test
    void shouldReduceStockWhenQuantityIsAvailable() {
        UUID productId = UUID.randomUUID();
        Dimensions dimensions = new Dimensions(10, 10, 10);
        ProductDetails productDetails = new ProductDetails("Product 1", "Description", dimensions);
        Money price = new Money("USD", new BigDecimal("100.00"));
        Stock stock = new Stock(10);

        ProductAggregate productAggregate = new ProductAggregate(productId, productDetails, price, stock);

        boolean result = productAggregate.reduceStock(5);

        assertTrue(result);
        assertEquals(5, productAggregate.getStock().getQuantity());
    }

    @Test
    void shouldNotReduceStockWhenQuantityIsInsufficient() {
        UUID productId = UUID.randomUUID();
        Dimensions dimensions = new Dimensions(10, 10, 10);
        ProductDetails productDetails = new ProductDetails("Product 1", "Description", dimensions);
        Money price = new Money("USD", new BigDecimal("100.00"));
        Stock stock = new Stock(10);

        ProductAggregate productAggregate = new ProductAggregate(productId, productDetails, price, stock);

        boolean result = productAggregate.reduceStock(15);

        assertFalse(result);
        assertEquals(10, productAggregate.getStock().getQuantity());
    }

    @Test
    void shouldUpdateProductPrice() {
        UUID productId = UUID.randomUUID();
        Dimensions dimensions = new Dimensions(10, 10, 10);
        ProductDetails productDetails = new ProductDetails("Product 1", "Description", dimensions);
        Money oldPrice = new Money("USD", new BigDecimal("100.00"));
        Stock stock = new Stock(10);

        ProductAggregate productAggregate = new ProductAggregate(productId, productDetails, oldPrice, stock);

        Money newPrice = new Money("USD", new BigDecimal("120.00"));
        productAggregate.updatePrice(newPrice);

        assertEquals(newPrice, productAggregate.getPrice());
    }
}
