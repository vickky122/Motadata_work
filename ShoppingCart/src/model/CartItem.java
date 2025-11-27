package model;

public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product,int quantity){
        if(product==null) throw new IllegalArgumentException("Product cannot be null");
        if(quantity<=0) throw new IllegalArgumentException("Quantity must be greater than 0");

        this.product=product;
        this.quantity=quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int delta){
        if(delta<=0) return ;
        this.quantity+=delta;
    }

    public void setQuantity(int quantity){
        if(quantity<=0) throw new IllegalArgumentException("Quantity must be positivee");
        this.quantity=quantity;
    }

    public double getTotalPrice(){
        return this.product.getPrice()*quantity;
    }
    @Override
    public String toString(){
        return product.getName() + " (x" + quantity + ") - ₹" + getTotalPrice();
    }
}
