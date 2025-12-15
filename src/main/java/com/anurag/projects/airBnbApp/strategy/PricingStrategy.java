package com.anurag.projects.airBnbApp.strategy;
import com.anurag.projects.airBnbApp.Entities.Inventory;
import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);
}
