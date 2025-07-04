package main.java.com.ecommerce.model.products.decorators;

import main.java.com.ecommerce.model.products.IProduct;
import main.java.com.ecommerce.model.products.ShippingItem;

public class ShippingProductDecorator extends ProductDecorator implements ShippingItem {
    private final double weight;
    public ShippingProductDecorator(IProduct product, double weight) {
        super(product);
        this.weight = weight;
    }
    @Override
    public String getName() {
        return product.getName() + "        " + (weight*1000) + "g";
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
