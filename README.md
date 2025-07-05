# Fawry Rise Journey - Full Stack Development Internship Challenge
## E-commerce System

This project is part of the **Fawry Rise Journey Full Stack Development Internship Challenge**. It implements a comprehensive e-commerce system using Java, demonstrating object-oriented programming principles, design patterns, and clean architecture.

## 🚀 Project Overview

The Fawry E-commerce System is a console-based Java application that simulates a complete e-commerce workflow including product management, shopping cart functionality, checkout process, and shipping services. The system handles various product types with different characteristics such as expiring products and shippable items.

## 🏗️ Class Diagram

![E-commerce System Class Diagram](docs/fawryEcommerceBackend1.drawio%20(1).svg)

### Design Patterns Implemented:
- **Decorator Pattern**: Used for extending product functionality (expiring products, shipping products)
- **Service Layer Pattern**: Separation of business logic into dedicated service classes
- **Interface Segregation**: Multiple specialized interfaces for different product capabilities

### Key Features:
- ✅ Product inventory management
- ✅ Shopping cart operations (add, remove, clear)
- ✅ Customer balance management
- ✅ Expiry date validation for perishable products
- ✅ Weight-based shipping cost calculation
- ✅ Complete checkout process with receipt generation
- ✅ Error handling for insufficient stock, expired products, and insufficient balance

## 📁 Project Structure

```
FawryEcommerceSystem/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── ecommerce/
│                   ├── Main.java                          # Entry point with demo scenarios
│                   ├── model/                             # Domain models
│                   │   ├── carts/
│                   │   │   └── Cart.java                  # Shopping cart implementation
│                   │   ├── customers/
│                   │   │   └── Customer.java              # Customer entity with balance management
│                   │   └── products/
│                   │       ├── IProduct.java              # Base product interface
│                   │       ├── IInventoryItem.java        # Inventory management interface
│                   │       ├── IInventoryProduct.java     # Combined product + inventory interface
│                   │       ├── IExpirable.java            # Expirable product interface
│                   │       ├── IShippable.java            # Shippable product interface
│                   │       ├── Product.java               # Base product implementation
│                   │       ├── ShippingItem.java          # Legacy shipping interface
│                   │       └── decorators/                # Decorator pattern implementations
│                   │           ├── ProductDecorator.java  # Abstract decorator base class
│                   │           ├── ExpiringProductDecorator.java    # Adds expiry functionality
│                   │           └── ShippingProductDecorator.java    # Adds shipping weight
│                   ├── services/                          # Business logic services
│                   │   ├── CheckoutService.java           # Handles checkout process
│                   │   └── ShippingService.java           # Calculates shipping costs
│                   └── utils/                             # Utility classes
│                       └── RecieptPrinter.java            # Receipt formatting utility
├── docs/                                                  # Diagrams
│
├── README.md   
└── FawryEcommerceSystem.iml
```

## 🔧 Technical Implementation

### Core Components

#### 1. Product Management
- **IProduct Interface**: Base contract for product identity and pricing
- **IInventoryItem Interface**: Manages stock quantities and reduction
- **IInventoryProduct Interface**: Combines product and inventory functionality
- **IExpirable Interface**: Handles expiry date validation
- **IShippable Interface**: Manages weight and shipping properties
- **Product Class**: Base implementation with name, price, and quantity
- **ProductDecorator**: Abstract base implementing all interfaces for flexible decoration
- **ExpiringProductDecorator**: Adds expiry date functionality with validation
- **ShippingProductDecorator**: Adds weight property for shipping calculations

#### 2. Shopping Cart System
- **Cart Class**: Manages product items and quantities with Map-based storage
- Validates product expiry during item addition
- Supports add, remove, and clear operations
- Prevents adding expired products with detailed error messages

#### 3. Customer Management
- **Customer Class**: Handles customer information, balance, and address
- Uses `BigDecimal` for precise monetary calculations
- Implements balance validation and withdrawal functionality

#### 4. Business Services
- **CheckoutService**: Orchestrates the complete checkout process
  - Validates cart contents and product expiry
  - Manages inventory reduction with rollback on failure
  - Calculates subtotal and applies shipping costs
  - Processes customer payment and generates receipt
- **ShippingService**: Calculates shipping costs based on item weights
  - Cost calculation: 27.2727 per kg
  - Handles only IShippable items
- **RecieptPrinter**: Utility for formatting checkout and shipment receipts


### Sample Output
```
Adding Cheese 200.0g (Expires in: 2025-07-15 ) to cart
Adding Biscuits 700.0g to cart
Adding Scratch Card to cart

Shipping items to: Shubra, Cairo, Egypt
** Shipment Notice **
1x  Biscuits         700g
2x  Cheese           200g
Total package weight: 1.1 kg

** Checkout Receipt **
1x  Scratch Card      5.00
1x  Biscuits        150.00
2x  Cheese          200.00
-------------------------------
Subtotal           355.00
Shipping Cost       30.00
Total              385.00
```

## 🧪 Demo Scenarios

The [`Main.java`](src/main/java/com/ecommerce/Main.java) class demonstrates:

1. **Successful Purchase**: Valid products with sufficient customer balance
2. **Expired Product Handling**: Attempting to add expired milk triggers exception
3. **Mixed Product Types**: Combination of expiring, shipping, and regular products
4. **Inventory Management**: Automatic stock reduction and rollback mechanisms

## 🎯 Key Architecture Features

### Interface Segregation
- Separate interfaces for different product capabilities (expirable, shippable, inventory)
- Clean separation allows flexible composition via decorators

### Decorator Pattern Implementation
- **ProductDecorator**: Implements all interfaces, delegates to wrapped product
- **ExpiringProductDecorator**: Adds expiry functionality to any product
- **ShippingProductDecorator**: Adds weight/shipping to any product
- Allows dynamic combination: `new ExpiringProductDecorator(new ShippingProductDecorator(product))`

### Error Handling & Validation
- Expired product validation at cart addition
- Insufficient balance with inventory rollback
- Stock validation with detailed error messages
- Proper BigDecimal usage for monetary precision

### Service Layer Architecture
- **CheckoutService**: Complex business logic with transaction management
- **ShippingService**: Specialized shipping calculations

### Utility
- **RecieptPrinter**: Utility for formatting receipt output console printing
