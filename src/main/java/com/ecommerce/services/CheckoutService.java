package main.java.com.ecommerce.services;

import main.java.com.ecommerce.model.carts.Cart;
import main.java.com.ecommerce.model.customers.Customer;
import main.java.com.ecommerce.model.products.IProduct;

import java.math.BigDecimal;
import java.util.Map;

public class CheckoutService {
    private ShippingService shippingService;

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void checkout(Customer customer, Cart customerCart) {
        if (customerCart.isEmpty()) {
            throw new IllegalStateException("Cart is empty. Cannot proceed to checkout.");
        }

        Map<IProduct, Integer> items = customerCart.getItems();
        for(var product: items.keySet()){
            if(product.isExpired()){
                throw new IllegalStateException("The "+ product.getName()+ " is expired!");
            }
        }
        BigDecimal subtotal = BigDecimal.valueOf(0.0);

        //Shipping items and getting the shipping price
        BigDecimal shippingCost = shippingService.shipItems(items, customer.getAddress());


        System.out.println("** Checkout receipt ** ");
        for (Map.Entry<IProduct, Integer> entry : items.entrySet()) {
            IProduct product = entry.getKey();
            int orderAmount = entry.getValue();
            if (product.getQuantity() < orderAmount) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }
            product.reduceQuantity(orderAmount);


            // totalItemPrice= price * quantity
            BigDecimal productPrice = BigDecimal.valueOf(product.getPrice());
            BigDecimal orderAmountBigDecimal = BigDecimal.valueOf(orderAmount);
            BigDecimal totalItemPrice= productPrice.multiply(orderAmountBigDecimal);

            System.out.printf("%-5d %-10s %10.2f%n", orderAmount, product.getName(), totalItemPrice.doubleValue());
            subtotal = subtotal.add(totalItemPrice);
        }
        System.out.println("-------------------------------");
        BigDecimal total = subtotal.add(shippingCost);
        customer.withdraw(total);

        System.out.println("Subtotal: " + subtotal.doubleValue());
        System.out.println("Shipping cost: " + shippingCost.doubleValue());
        System.out.println("Total: " + total.doubleValue());

        customerCart.clear();
    }
}
