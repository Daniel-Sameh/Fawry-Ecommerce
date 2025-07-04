package main.java.com.ecommerce.services;

import main.java.com.ecommerce.model.carts.Cart;
import main.java.com.ecommerce.model.customers.Customer;
import main.java.com.ecommerce.model.products.IProduct;
import main.java.com.ecommerce.model.products.ShippingItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShippingService {

    private final double COST_PER_KG=27.2727;
    public ShippingService(){}

    public BigDecimal shipItems(Map<IProduct, Integer> cartItems, String address){
        System.out.println("Shipping items to: " + address);
        System.out.println("** Shipment Notice **");

        BigDecimal totalWeight= BigDecimal.valueOf(0.0); //in kg

        for(Map.Entry<IProduct, Integer> entry : cartItems.entrySet()){
            IProduct item = entry.getKey();
            IProduct checkShippingItem = (IProduct) item.getShippingItem();
            ShippingItem shippingItem;
            if(checkShippingItem == null){
                continue;
            }
            shippingItem= (ShippingItem) checkShippingItem;

            int quantity = entry.getValue();
            BigDecimal itemWeight = BigDecimal.valueOf(shippingItem.getWeight() * quantity);
            totalWeight = totalWeight.add(itemWeight);
            System.out.printf("%dx  %-15s %2.0fg%n", quantity, shippingItem.getName(), shippingItem.getWeight() * 1000);
        }

        System.out.println("Total package weight: " + totalWeight + " kg\n");

        return totalWeight.multiply(BigDecimal.valueOf(COST_PER_KG));
    }

}
