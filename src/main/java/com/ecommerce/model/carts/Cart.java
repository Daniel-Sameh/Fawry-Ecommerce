package main.java.com.ecommerce.model.carts;

import main.java.com.ecommerce.model.products.IInventoryProduct;
import main.java.com.ecommerce.model.products.IProduct;
import main.java.com.ecommerce.model.products.Product;
import main.java.com.ecommerce.model.products.decorators.ExpiringProductDecorator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Map<IInventoryProduct, Integer> items = new HashMap<>();

    public Cart() {}

    public Map<IInventoryProduct, Integer> getItems() {
        return items;
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }
    public void addItem(IInventoryProduct product, int amount) {
        if(product instanceof ExpiringProductDecorator && ((ExpiringProductDecorator) product).isExpired()) {
            throw new IllegalStateException("The Product has expired on "+ ((ExpiringProductDecorator) product).getExpiryDate());
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        items.merge(product, amount, Integer::sum);

    }

    public void removeItem(IInventoryProduct product, int amount) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        if(!items.containsKey(product)) {
            throw new IllegalArgumentException("Product not found in cart");
        }

        if (amount == items.getOrDefault(product, 0)) {
            items.remove(product);
        }else if(amount > items.getOrDefault(product, 0)){
            throw new IllegalArgumentException("Cannot remove more than available cart quantity");
        }else{
            int currentQuantity = items.get(product);
            items.put(product, currentQuantity - amount);
        }
    }

    public void clear(){
        items.clear();
    }

}
