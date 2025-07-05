package main.java.com.ecommerce.model.products.decorators;

import main.java.com.ecommerce.model.products.IInventoryProduct;
import main.java.com.ecommerce.model.products.IProduct;
import main.java.com.ecommerce.model.products.IShippable;
import main.java.com.ecommerce.model.products.ShippingItem;

import java.time.LocalDate;

public class ShippingProductDecorator extends ProductDecorator implements IShippable{
    private final double weight;
    public ShippingProductDecorator(IInventoryProduct product, double weight) {
        super(product);
        this.weight = weight;
    }
    @Override
    public String getFullName(){
        return product.getFullName() + " " + (weight*1000) + "g";
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isShippable() {
        return true;
    }
}
