package main.java.com.ecommerce.model.carts;

import main.java.com.ecommerce.model.products.IProduct;

import java.math.BigDecimal;

public class CartItem {
    private IProduct product;
    private int quantity;
    public CartItem(IProduct product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public IProduct getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }
    public double getTotalPrice() {
        return product.getPrice()* quantity;
    }
    public String getName(){
        return quantity+"x "+product.getName();
    }

}
