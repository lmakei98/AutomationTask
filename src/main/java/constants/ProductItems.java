package constants;

import utils.Money;

import java.math.BigDecimal;

public enum ProductItems {
    FRESHLY_BAKED_MUFFINS("Freshly Baked Muffins Daily",
            new Money(BigDecimal.valueOf(10.00)));

    private final String displayName;
    private final Money price;

    ProductItems(String displayName, Money price) {
        this.displayName = displayName;
        this.price = price;
    }

    public String getName() {
        return displayName;
    }

    public Money getPrice() {
        return price;
    }
}
