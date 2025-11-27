package service;

import model.Discount;
import model.DiscountType;

import java.util.HashMap;
import java.util.Map;

public class DiscountService {

    private final Map<String, Discount> discounts = new HashMap<>();

    public DiscountService() {
        // Preload some example coupons
        // NEW10     -> 10% off, no minimum
        // FLAT100   -> ₹100 off, min subtotal ₹1000
        // BIGSALE5  -> 5% off, min subtotal ₹500
        addDiscount(new Discount("NEW10", DiscountType.PERCENTAGE, 10.0, 0.0));
        addDiscount(new Discount("FLAT100", DiscountType.FLAT, 100.0, 1000.0));
        addDiscount(new Discount("BIGSALE5", DiscountType.PERCENTAGE, 5.0, 500.0));
    }

    private void addDiscount(Discount discount) {
        discounts.put(discount.getCode(), discount);
    }

    public boolean isValid(String code) {
        if (code == null) return false;
        return discounts.containsKey(code.trim().toUpperCase());
    }

    public Discount getDiscount(String code) {
        if (code == null) return null;
        return discounts.get(code.trim().toUpperCase());
    }

    public double calculateDiscount(double subtotal, String code) {
        Discount discount = getDiscount(code);
        if (discount == null) return 0.0;

        if (subtotal < discount.getMinAmount()) {
            return 0.0;
        }

        if (discount.getType() == DiscountType.PERCENTAGE) {
            return subtotal * (discount.getValue() / 100.0);
        } else {
            return discount.getValue();
        }
    }
}
