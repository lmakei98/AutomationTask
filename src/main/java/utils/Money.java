package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

/**
 * This class represents a monetary value with a specific currency.
 */
public class Money {
    private final BigDecimal amount;
    private final Currency currency;

    /**
     * Constructor for the Money class.
     *
     * @param amount the monetary amount.
     */
    public Money(BigDecimal amount) {
        this.amount = Objects.requireNonNull(amount, "Amount cannot be null")
                .setScale(2, RoundingMode.HALF_UP);
        this.currency = Currency.getInstance("EUR");
    }

    /**
     * Returns the string representation of the monetary value.
     *
     * @return the string representation of the monetary value.
     */
    @Override
    public String toString() {
        return String.format("%s%s", currency.getSymbol(), amount);
    }

    /**
     * Checks if this Money object is equal to another object.
     *
     * @param o the object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount.equals(money.amount) && currency.equals(money.currency);
    }

    /**
     * Returns the hash code of this Money object.
     *
     * @return the hash code of this Money object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    /**
     * Adds another Money object to this Money object.
     *
     * @param other the Money object to add.
     * @return a new Money object representing the sum.
     */
    public Money add(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    /**
     * Multiplies this Money object by a given multiplier.
     *
     * @param multiplier the multiplier to apply.
     * @return a new Money object representing the product.
     */
    public Money multiply(BigDecimal multiplier) {
        return new Money(this.amount.multiply(multiplier));
    }

    /**
     * Subtracts another Money object from this Money object.
     *
     * @param other the Money object to subtract.
     * @return a new Money object representing the difference.
     */
    public Money subtract(Money other) {
        return new Money(this.amount.subtract(other.amount));
    }
}