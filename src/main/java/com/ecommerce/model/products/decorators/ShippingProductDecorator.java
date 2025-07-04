package main.java.com.ecommerce.model.products.decorators;

import main.java.com.ecommerce.model.products.IProduct;
import main.java.com.ecommerce.model.products.ShippingItem;

import java.time.LocalDate;

public class ShippingProductDecorator extends ProductDecorator implements ShippingItem {
    private final double weight;
    public ShippingProductDecorator(IProduct product, double weight) {
        super(product);
        this.weight = weight;
    }
    @Override
    public String getFullName(){
        return product.getFullName() + " " + (weight*1000) + "g";
    }
    @Override
    public String getName() {
        return product.getName();
    }


    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isExpired() {
        return product.isExpired();
    }

    @Override
    public ShippingItem getShippingItem(){
        return this;
    }
}
