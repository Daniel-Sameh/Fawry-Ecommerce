package main.java.com.ecommerce.services;

import main.java.com.ecommerce.model.products.IInventoryProduct;
import main.java.com.ecommerce.model.products.IShippable;
import main.java.com.ecommerce.utils.RecieptPrinter;

import java.math.BigDecimal;
import java.util.Map;

public class ShippingService {

    private static final BigDecimal COST_PER_KG = BigDecimal.valueOf(27.2727);
    private static final RecieptPrinter receiptPrinter = new RecieptPrinter();
    public ShippingService(){}

    public BigDecimal shipItems(Map<IInventoryProduct, Integer> cartItems, String address){
        BigDecimal totalWeight= BigDecimal.ZERO;

        for(Map.Entry<IInventoryProduct, Integer> entry : cartItems.entrySet()){
            IInventoryProduct item = entry.getKey();
            if(!(item instanceof IShippable)){
                continue; //Skip items that are not shippable
            }

            IShippable shippingItem= (IShippable) item;

            int quantity = entry.getValue();
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero");
            }

            BigDecimal itemWeight = BigDecimal.valueOf(shippingItem.getWeight() * quantity);
            totalWeight = totalWeight.add(itemWeight);

        }

        receiptPrinter.printShipmentReceipt(cartItems, totalWeight, address);
        return totalWeight.multiply(COST_PER_KG);
    }

}
