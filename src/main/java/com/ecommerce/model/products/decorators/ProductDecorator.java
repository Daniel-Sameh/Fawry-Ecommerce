package main.java.com.ecommerce.model.products.decorators;

import main.java.com.ecommerce.model.products.IExpirable;
import main.java.com.ecommerce.model.products.IInventoryProduct;
import main.java.com.ecommerce.model.products.IProduct;
import main.java.com.ecommerce.model.products.IShippable;

import java.time.LocalDate;

public abstract class ProductDecorator implements IInventoryProduct, IShippable, IExpirable {
    protected IInventoryProduct product;
    public ProductDecorator(IInventoryProduct product) {
        this.product = product;
    }

    @Override
    public String getFullName() {
        return product.getFullName();
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

    @Override public boolean isShippable() {
        return product instanceof IShippable && ((IShippable)product).isShippable();
    }
    @Override public double getWeight() {
        if (product instanceof IShippable) {
            return ((IShippable)product).getWeight();
        }
        return 0;
    }

    @Override
    public boolean isExpired() {
        return product instanceof IExpirable && ((IExpirable)product).isExpired();
    }

    @Override
    public LocalDate getExpiryDate() {
        if (product instanceof IExpirable expirable) {
            return expirable.getExpiryDate();
        }
        return LocalDate.MAX;
    }
}
