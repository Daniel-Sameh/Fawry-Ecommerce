package main.java.com.ecommerce.model.products.decorators;

import main.java.com.ecommerce.model.products.IProduct;

import java.time.LocalDate;

public class ExpiringProductDecorator extends ProductDecorator {
    private LocalDate expiryDate;

    public ExpiringProductDecorator(IProduct product, LocalDate expiryDate) {
        super(product);
        this.expiryDate = expiryDate;
    }

    @Override
    public String getName() {
        return product.getName() + " (Expiring in: " + expiryDate + ")";
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

}
