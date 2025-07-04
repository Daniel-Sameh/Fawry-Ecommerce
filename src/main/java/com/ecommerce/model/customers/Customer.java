package main.java.com.ecommerce.model.customers;

import main.java.com.ecommerce.model.carts.Cart;

import java.math.BigDecimal;

public class Customer {
    private String name;
    private BigDecimal balance; //BigDecimal is safer for money than double
    private String address;

    public Customer(String name, BigDecimal balance, String address) {
        this.name = name;
        this.balance = balance;
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getAddress() {
        return address;
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(balance) > 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance = balance.subtract(amount);
    }
}
