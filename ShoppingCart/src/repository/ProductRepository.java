package repository;

import model.Product;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

    private final Map<String, Product> products = new HashMap<>();

    public ProductRepository() {
        // Preload some sample products (like a tiny catalog)
        addProduct(new Product("P1", "Apple iPhone 15", 75000));
        addProduct(new Product("P2", "Noise Cancelling Headphones", 4500));
        addProduct(new Product("P3", "Gaming Laptop", 95000));
        addProduct(new Product("P4", "Mechanical Keyboard", 3500));
        addProduct(new Product("P5", "Wireless Mouse", 1200));
    }

    private void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Product getProductById(String id) {
        if (id == null) return null;
        return products.get(id.trim());
    }

    public boolean exists(String id) {
        if (id == null) return false;
        return products.containsKey(id.trim());
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }
}
