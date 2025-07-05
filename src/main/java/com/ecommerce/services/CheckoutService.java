package main.java.com.ecommerce.services;

import main.java.com.ecommerce.model.carts.Cart;
import main.java.com.ecommerce.model.customers.Customer;
import main.java.com.ecommerce.model.products.IExpirable;
import main.java.com.ecommerce.model.products.IInventoryProduct;
import main.java.com.ecommerce.utils.RecieptPrinter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CheckoutService {
    private ShippingService shippingService;
    private static final RecieptPrinter receiptPrinter = new RecieptPrinter();

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void checkout(Customer customer, Cart customerCart) {
        if (customerCart.isEmpty()) {
            throw new IllegalStateException("Cart is empty. Cannot proceed to checkout.");
        }

        Map<IInventoryProduct, Integer> items = customerCart.getItems();
        for(var product: items.keySet()){
            if(product instanceof IExpirable expirableProduct && expirableProduct.isExpired()){
                throw new IllegalStateException("The "+ product.getName()+ " is expired!");
            }
        }

        BigDecimal subtotal = BigDecimal.ZERO;


        Map<IInventoryProduct, BigDecimal> totalItemsPrices = new HashMap<>();

        boolean completedTransaction = true;
        BigDecimal tmpCustomerBalance = customer.getBalance();
        Map<IInventoryProduct, Integer> itemsReducedQuantityHistory = new HashMap<>();

        for (Map.Entry<IInventoryProduct, Integer> entry : items.entrySet()) {
            IInventoryProduct product = entry.getKey();
            int orderAmount = entry.getValue();

            // Check and reduce the product quantity in inventory
            if (product.getQuantity() < orderAmount) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }
            product.reduceQuantity(orderAmount);
            itemsReducedQuantityHistory.put(product, orderAmount);


            // totalItemPrice= price * quantity
            BigDecimal productPrice = BigDecimal.valueOf(product.getPrice());
            BigDecimal orderAmountBigDecimal = BigDecimal.valueOf(orderAmount);
            BigDecimal totalItemPrice= productPrice.multiply(orderAmountBigDecimal);

            if (totalItemPrice.compareTo(tmpCustomerBalance) > 0) {
                completedTransaction=false;
                break;
            }

            tmpCustomerBalance = tmpCustomerBalance.subtract(totalItemPrice);
            totalItemsPrices.put(product, totalItemPrice);

            subtotal = subtotal.add(totalItemPrice);
        }

        if(!completedTransaction) {
            // Restore the quantities of the products in case of insufficient balance
            for (var entry : itemsReducedQuantityHistory.entrySet()) {
                IInventoryProduct product = entry.getKey();
                int orderAmount = entry.getValue();
                product.reduceQuantity(-orderAmount); // Restore the quantity
//                System.out.println("Restored " + orderAmount + " of " + product.getName() + " to inventory= "+ product.getQuantity());
            }
            throw new IllegalStateException("Insufficient balance for the transaction. Please add funds to your account.");
        }

        //Shipping items and getting the shipping price
        BigDecimal shippingCost = shippingService.shipItems(items, customer.getAddress());
        BigDecimal total = subtotal.add(shippingCost);

        customer.withdraw(total);

        receiptPrinter.printCheckoutReceipt(items, totalItemsPrices, subtotal, shippingCost, total);

        customerCart.clear();
    }
}
