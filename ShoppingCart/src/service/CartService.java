package service;

import model.CartItem;
import model.Product;
import repository.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {

    private final Map<String, CartItem> items = new HashMap<>();
    private final ProductRepository productRepository;
    private final DiscountService discountService;

    private String appliedCouponCode;

    public CartService(ProductRepository productRepository, DiscountService discountService) {
        this.productRepository = productRepository;
        this.discountService = discountService;
    }

    public boolean addItem(String productId, int quantity) {
        if (quantity <= 0) return false;
        Product product = productRepository.getProductById(productId);
        if (product == null) {
            return false;
        }

        CartItem existing = items.get(productId);
        if (existing != null) {
            existing.increaseQuantity(quantity);
        } else {
            items.put(productId, new CartItem(product, quantity));
        }
        return true;
    }

    public boolean removeItem(String productId) {
        return items.remove(productId) != null;
    }

    public boolean updateQuantity(String productId, int newQuantity) {
        CartItem item = items.get(productId);
        if (item == null) return false;

        if (newQuantity <= 0) {
            items.remove(productId);
        } else {
            item.setQuantity(newQuantity);
        }
        return true;
    }

    public List<CartItem> getAllItems() {
        return new ArrayList<>(items.values());
    }

    public boolean applyCoupon(String code) {
        if (code == null) return false;
        code = code.trim().toUpperCase();
        if (discountService.isValid(code)) {
            this.appliedCouponCode = code;
            return true;
        }
        return false;
    }

    public void clearCoupon() {
        this.appliedCouponCode = null;
    }

    public double calculateSubtotal() {
        double subtotal = 0.0;
        for (CartItem item : items.values()) {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }

    public double calculateDiscountAmount() {
        if (appliedCouponCode == null) return 0.0;
        double subtotal = calculateSubtotal();
        return discountService.calculateDiscount(subtotal, appliedCouponCode);
    }

    public double calculateTotal() {
        double subtotal = calculateSubtotal();
        double discount = calculateDiscountAmount();
        double total = subtotal - discount;
        return Math.max(total, 0.0);
    }

    public String getAppliedCouponCode() {
        return appliedCouponCode;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
