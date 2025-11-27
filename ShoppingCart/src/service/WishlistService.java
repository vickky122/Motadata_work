package service;

import repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class WishlistService {
    private final List<String> wishlist=new ArrayList<>();
    private final ProductRepository productRepository;

    public WishlistService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    public boolean addToWishlist(String productId){
        if (!productRepository.exists(productId)) {
            return false;
        }
        if (!wishlist.contains(productId)) {
            wishlist.add(productId);
        }
        return true;
    }
    public boolean removeFromWishlist(String productId) {
        return wishlist.remove(productId);
    }

    public List<String> getWishlistProductIds() {
        return new ArrayList<>(wishlist);
    }

    public boolean contains(String productId) {
        return wishlist.contains(productId);
    }
}
