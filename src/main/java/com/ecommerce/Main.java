package main.java.com.ecommerce;

import main.java.com.ecommerce.model.carts.Cart;
import main.java.com.ecommerce.model.customers.Customer;
import main.java.com.ecommerce.model.products.IProduct;
import main.java.com.ecommerce.model.products.Product;
import main.java.com.ecommerce.model.products.decorators.ExpiringProductDecorator;
import main.java.com.ecommerce.model.products.decorators.ShippingProductDecorator;
import main.java.com.ecommerce.services.CheckoutService;
import main.java.com.ecommerce.services.ShippingService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ShippingService shippingService = new ShippingService();
        CheckoutService checkoutService = new CheckoutService(shippingService);
        IProduct cheese = new ExpiringProductDecorator(
                         new ShippingProductDecorator(
                         new Product("Cheese", 100, 5),0.2), LocalDate.now().plusDays(10));
        IProduct biscuits= new ShippingProductDecorator(new Product("Biscuits", 150, 10), 0.7);
        IProduct tv = new ShippingProductDecorator(new Product("TV", 1000, 2), 10.0);
        IProduct scratchCard = new Product("Scratch Card", 5, 100);

        Cart cart = new Cart();

        Customer daniel = new Customer("Daniel", BigDecimal.valueOf(150.0),"Shubra, Cairo, Egypt");

        cart.addItem(cheese, 2);
        cart.addItem(biscuits, 1);
        cart.addItem(scratchCard, 1);
        checkoutService.checkout(daniel, cart);



    }
}