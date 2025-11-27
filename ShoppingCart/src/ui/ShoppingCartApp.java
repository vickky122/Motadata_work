package ui;

import model.CartItem;
import model.Product;
import repository.ProductRepository;
import service.CartService;
import service.DiscountService;
import service.WishlistService;

import java.util.List;
import java.util.Scanner;

public class ShoppingCartApp {

    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepository();
        DiscountService discountService = new DiscountService();
        CartService cartService = new CartService(productRepository, discountService);
        WishlistService wishlistService = new WishlistService(productRepository);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Shopping Cart System ---");
            System.out.println("1. View Products");
            System.out.println("2. Add Item to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Update Cart Item Quantity");
            System.out.println("5. Remove Item from Cart");
            System.out.println("6. Move Item to Wishlist");
            System.out.println("7. View Wishlist");
            System.out.println("8. Apply Coupon");
            System.out.println("9. View Totals");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("\nAvailable Products:");
                    for (Product p : productRepository.getAllProducts()) {
                        System.out.println(p.getId() + " - " + p.getName() + " (₹" + p.getPrice() + ")");
                    }
                }
                case 2 -> {
                    System.out.print("Enter Product ID to add: ");
                    String pid = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty;
                    try {
                        qty = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity.");
                        break;
                    }
                    boolean added = cartService.addItem(pid, qty);
                    System.out.println(added ? "Item added to cart." : "Failed to add item (invalid product or quantity).");
                }
                case 3 -> {
                    System.out.println("\nYour Cart:");
                    List<CartItem> items = cartService.getAllItems();
                    if (items.isEmpty()) {
                        System.out.println("Cart is empty.");
                    } else {
                        for (CartItem item : items) {
                            System.out.println("- " + item);
                        }
                    }
                }
                case 4 -> {
                    System.out.print("Enter Product ID to update: ");
                    String pid = sc.nextLine();
                    System.out.print("Enter new quantity (0 to remove): ");
                    int qty;
                    try {
                        qty = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity.");
                        break;
                    }
                    boolean updated = cartService.updateQuantity(pid, qty);
                    System.out.println(updated ? "Quantity updated." : "Item not found in cart.");
                }
                case 5 -> {
                    System.out.print("Enter Product ID to remove: ");
                    String pid = sc.nextLine();
                    boolean removed = cartService.removeItem(pid);
                    System.out.println(removed ? "Item removed from cart." : "Item not found in cart.");
                }
                case 6 -> {
                    System.out.print("Enter Product ID to move to wishlist: ");
                    String pid = sc.nextLine();
                    boolean existsInCart = cartService.removeItem(pid);
                    if (!existsInCart) {
                        System.out.println("Item not found in cart.");
                        break;
                    }
                    boolean addedToWishlist = wishlistService.addToWishlist(pid);
                    System.out.println(addedToWishlist
                            ? "Item moved to wishlist."
                            : "Failed to move to wishlist (invalid product).");
                }
                case 7 -> {
                    System.out.println("\nYour Wishlist:");
                    List<String> ids = wishlistService.getWishlistProductIds();
                    if (ids.isEmpty()) {
                        System.out.println("Wishlist is empty.");
                    } else {
                        for (String id : ids) {
                            Product p = productRepository.getProductById(id);
                            if (p != null) {
                                System.out.println("- " + p.getId() + " - " + p.getName() + " (₹" + p.getPrice() + ")");
                            }
                        }
                    }
                }
                case 8 -> {
                    System.out.print("Enter coupon code: ");
                    String code = sc.nextLine();
                    boolean applied = cartService.applyCoupon(code);
                    System.out.println(applied ? "Coupon applied." : "Invalid coupon code.");
                }
                case 9 -> {
                    double subtotal = cartService.calculateSubtotal();
                    double discount = cartService.calculateDiscountAmount();
                    double total = cartService.calculateTotal();
                    System.out.println("\nCart Summary:");
                    System.out.println("Subtotal: ₹" + subtotal);
                    System.out.println("Discount: ₹" + discount + (cartService.getAppliedCouponCode() != null
                            ? " (Code: " + cartService.getAppliedCouponCode() + ")"
                            : ""));
                    System.out.println("Total: ₹" + total);
                }
                case 10 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
