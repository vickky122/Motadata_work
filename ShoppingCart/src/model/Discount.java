package model;

public class Discount {
    private final String code;
    private final DiscountType type;
    private final double value;
    private final double minAmount;

    public Discount(String code, DiscountType type, double value, double minAmount){
        if(code==null || code.isBlank()) throw new IllegalArgumentException("code cannot be blank");

        this.code=code;
        this.type=type;
        this.value=value;
        this.minAmount=minAmount;
    }

    public String getCode() {
        return code;
    }

    public DiscountType getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public double getMinAmount() {
        return minAmount;
    }
}
