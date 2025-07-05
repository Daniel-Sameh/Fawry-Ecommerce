package main.java.com.ecommerce.model.products;

import java.time.LocalDate;

public interface IExpirable {
    LocalDate getExpiryDate();
    boolean isExpired();
}
