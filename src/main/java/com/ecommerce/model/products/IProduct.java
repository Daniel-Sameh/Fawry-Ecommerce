package main.java.com.ecommerce.model.products;

//import java.math.BigDecimal;

public interface IProduct {
    String getFullName();
    String getName();
    double getPrice();
    int getQuantity();
    void reduceQuantity(int amount);
    boolean isExpired();
    ShippingItem getShippingItem();
}
