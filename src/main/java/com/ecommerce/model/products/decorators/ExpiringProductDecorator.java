package main.java.com.ecommerce.model.products.decorators;

import main.java.com.ecommerce.model.products.*;

import java.time.LocalDate;

public class ExpiringProductDecorator extends ProductDecorator implements IExpirable {

    private LocalDate expiryDate;

    public ExpiringProductDecorator(IInventoryProduct product, LocalDate expiryDate) {
        super(product);
        this.expiryDate = expiryDate;
    }

    @Override
    public String getFullName() {
        return product.getFullName()+ " (Expires in: "+ expiryDate+" )";
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

}
