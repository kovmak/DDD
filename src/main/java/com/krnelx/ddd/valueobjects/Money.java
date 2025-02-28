package com.krnelx.ddd.valueobjects;

import com.krnelx.ddd.exceptions.InvalidMoneyOperationException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Objects;

public final class Money {

    private final String currency;
    private final BigDecimal amount;

    public Money(String currency, BigDecimal amount) {
        if (currency == null || currency.trim().isEmpty()) {
            throw new InvalidMoneyOperationException("Currency cannot be null or empty.");
        }
        if (amount == null) {
            throw new InvalidMoneyOperationException("Amount cannot be null.");
        }
        this.currency = currency;
        this.amount = amount;
    }

    public Money add(Money other) {
        validateCurrency(other);
        return new Money(this.currency, this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        validateCurrency(other);
        return new Money(this.currency, this.amount.subtract(other.amount));
    }

    public String format() {
        Currency curr = Currency.getInstance(currency);
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setCurrency(curr);
        return format.format(amount);
    }

    private void validateCurrency(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new InvalidMoneyOperationException("Cannot operate on different currencies.");
        }
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money money)) return false;
        return currency.equals(money.currency) && amount.equals(money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

    @Override
    public String toString() {
        return "Money{" +
            "currency='" + currency + '\'' +
            ", amount=" + amount +
            '}';
    }
}
