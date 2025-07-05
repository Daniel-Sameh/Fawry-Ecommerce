package main.java.com.ecommerce.model.products;

public interface IInventoryItem {
    int getQuantity();
    void reduceQuantity(int amount);
}
