package main.java.com.ecommerce.model.products;

//import java.math.BigDecimal;

public interface IProduct {
    String getName();
    double getPrice();
    int getQuantity();
    void reduceQuantity(int amount);
}
