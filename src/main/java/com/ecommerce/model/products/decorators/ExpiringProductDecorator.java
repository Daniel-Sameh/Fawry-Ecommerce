package main.java.com.ecommerce.model.products.decorators;

import main.java.com.ecommerce.model.products.IProduct;
import main.java.com.ecommerce.model.products.ShippingItem;

import java.time.LocalDate;

public class ExpiringProductDecorator extends ProductDecorator {
    private LocalDate expiryDate;

    public ExpiringProductDecorator(IProduct product, LocalDate expiryDate) {
        super(product);
        this.expiryDate = expiryDate;
    }

    @Override
    public String getFullName() {
        return product.getFullName()+ " (Expires in: "+ expiryDate+" )";
    }

    @Override
    public String getName(){
        return product.getName();
    }

    @Override
    public double getPrice() {
        if (isExpired()) throw new IllegalStateException("The Product has expired on "+ expiryDate);
        return super.getPrice();
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public ShippingItem getShippingItem(){
        return product.getShippingItem();
    }

}
