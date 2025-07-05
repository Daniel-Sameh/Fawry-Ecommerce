package main.java.com.ecommerce.model.products;

public class Product implements IInventoryProduct {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getFullName(){
        return name;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void reduceQuantity(int amount) {
        if (amount <= quantity) {
            quantity -= amount;
        }else{
            throw new IllegalArgumentException("Insufficient quantity available");
        }
    }
}
