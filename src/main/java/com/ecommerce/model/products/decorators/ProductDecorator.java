package main.java.com.ecommerce.model.products.decorators;

import main.java.com.ecommerce.model.products.IProduct;

public abstract class ProductDecorator implements IProduct {
    protected IProduct product;
    public ProductDecorator(IProduct product) {
        this.product = product;
    }

    @Override
    public String getName() {
        return product.getName();
    }
    @Override
    public double getPrice() {
        return product.getPrice();
    }
    @Override
    public int getQuantity() {
        return product.getQuantity();
    }
    @Override
    public void reduceQuantity(int amount) {
        product.reduceQuantity(amount);
    }
}
