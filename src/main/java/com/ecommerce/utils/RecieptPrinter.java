package main.java.com.ecommerce.utils;

import main.java.com.ecommerce.model.products.IInventoryProduct;
import main.java.com.ecommerce.model.products.IShippable;

import java.math.BigDecimal;
import java.util.Map;

public class RecieptPrinter {
    public RecieptPrinter() {}

    public void printCheckoutReceipt(Map<IInventoryProduct, Integer> items, Map<IInventoryProduct, BigDecimal> totalItemPrices,
                             BigDecimal subTotal, BigDecimal shipmentCost, BigDecimal total) {
        System.out.println("** Checkout Receipt **");
        for(var entry: items.entrySet()){
            IInventoryProduct product = entry.getKey();
            int orderAmount = entry.getValue();
            BigDecimal productPrice = BigDecimal.valueOf(product.getPrice());
            BigDecimal orderAmountBigDecimal = BigDecimal.valueOf(orderAmount);
            BigDecimal totalItemPrice = totalItemPrices.get(product);

            System.out.printf("%dx  %-15s %2.2f%n", orderAmount, product.getName(), totalItemPrice.doubleValue());
        }
        System.out.println("-------------------------------");
        System.out.printf("%-15s %10.2f%n", "Subtotal", subTotal.doubleValue());
        System.out.printf("%-15s %10.2f%n", "Shipping Cost", shipmentCost.doubleValue());
        System.out.printf("%-15s %10.2f%n", "Total", total.doubleValue());

    }

    public void printShipmentReceipt(Map<IInventoryProduct, Integer> items, BigDecimal totalWeight, String address){
        System.out.println("Shipping items to: " + address);
        System.out.println("** Shipment Notice **");

        for (var entry : items.entrySet()) {
            IInventoryProduct item = entry.getKey();
            if (!(item instanceof IShippable)) {
                continue; // Skip items that are not shippable
            }
            IShippable shippableItem = (IShippable) item;

            int quantity = entry.getValue();
            System.out.printf("%dx  %-15s %2.0fg%n", quantity, item.getName(), shippableItem.getWeight() * 1000);
        }

        System.out.println("Total package weight: " + totalWeight + " kg\n");
    }
}
