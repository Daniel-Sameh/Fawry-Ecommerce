package main.java.com.ecommerce;

import main.java.com.ecommerce.model.carts.Cart;
import main.java.com.ecommerce.model.customers.Customer;
import main.java.com.ecommerce.model.products.IInventoryProduct;
import main.java.com.ecommerce.model.products.IProduct;
import main.java.com.ecommerce.model.products.Product;
import main.java.com.ecommerce.model.products.decorators.ExpiringProductDecorator;
import main.java.com.ecommerce.model.products.decorators.ProductDecorator;
import main.java.com.ecommerce.model.products.decorators.ShippingProductDecorator;
import main.java.com.ecommerce.services.CheckoutService;
import main.java.com.ecommerce.services.ShippingService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ShippingService shippingService = new ShippingService();
        CheckoutService checkoutService = new CheckoutService(shippingService);
        IInventoryProduct cheese = new ExpiringProductDecorator(
                         new ShippingProductDecorator(
                         new Product("Cheese", 100, 5),0.2), LocalDate.now().plusDays(10));
        IInventoryProduct milk = new ExpiringProductDecorator(
                         new ShippingProductDecorator(
                         new Product("Milk", 50, 20), 0.1), LocalDate.now().minusDays(5));
        IInventoryProduct biscuits= new ShippingProductDecorator(new Product("Biscuits", 150, 10), 0.7);
        IInventoryProduct tv = new ShippingProductDecorator(new Product("TV", 1000, 2), 10.0);
        IInventoryProduct scratchCard = new Product("Scratch Card", 5, 100);

        Cart cart = new Cart();

        Customer daniel = new Customer("Daniel", BigDecimal.valueOf(500),"Shubra, Cairo, Egypt");

        System.out.println("Adding "+ cheese.getFullName()+" to cart");
        cart.addItem(cheese, 2);
        System.out.println("Adding "+ biscuits.getFullName() +" to cart");
        cart.addItem(biscuits, 1);
        System.out.println("Adding "+ scratchCard.getFullName() +" to cart\n");
        cart.addItem(scratchCard, 1);


        checkoutService.checkout(daniel, cart);

        System.out.println("\nDaniel balance now is: " + daniel.getBalance());
        System.out.println();

        Customer sameh = new Customer("Sameh", BigDecimal.valueOf(400), "Alexandria, Egypt");

        //Will result in an error for insufficient balance
//        System.out.println("\n\nAdding TV to the cart");
//        cart.addItem(tv,1);

        //Will result in an error for that the milk is expired
        System.out.println("Adding milk to cart");
        cart.addItem(milk, 3);

        checkoutService.checkout(sameh, cart);

    }
}