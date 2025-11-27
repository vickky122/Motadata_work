package model;

public class Product {
    private final String id;
    private final String name;
    private final double price;

    public Product(String id,String name, double price){
        if(id==null || id.isBlank()) throw  new IllegalArgumentException("Id cannot be null or blank");
        if(name==null || name.isBlank()) throw  new IllegalArgumentException("Name cannot be null or blank");
        if(price<0) throw  new IllegalArgumentException("Price cannot be negative");

        this.id=id;
        this.name=name;
        this.price=price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
