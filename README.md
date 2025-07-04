# Fawry Rise Journey - Full Stack Development Internship Challenge
## E-commerce System

This project is part of the **Fawry Rise Journey Full Stack Development Internship Challenge**. It implements a comprehensive e-commerce system using Java, demonstrating object-oriented programming principles, design patterns, and clean architecture.

## 🚀 Project Overview

The Fawry E-commerce System is a console-based Java application that simulates a complete e-commerce workflow including product management, shopping cart functionality, checkout process, and shipping services. The system handles various product types with different characteristics such as expiring products and shippable items.

## 🏗️ Class Diagram

![E-commerce System Class Diagram](docs/fawryEcommerceBackend.drawio.svg)

### Design Patterns Implemented:
- **Decorator Pattern**: Used for extending product functionality (expiring products, shipping products)
- **Service Layer Pattern**: Separation of business logic into dedicated service classes
- **Interface Segregation**: Clean interfaces for products and shipping items

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
│                   │       ├── IProduct.java              # Product interface
│                   │       ├── Product.java               # Base product implementation
│                   │       ├── ShippingItem.java          # Interface for shippable items
│                   │       └── decorators/                # Decorator pattern implementations
│                   │           ├── ProductDecorator.java  # Abstract decorator base class
│                   │           ├── ExpiringProductDecorator.java    # Adds expiry functionality
│                   │           └── ShippingProductDecorator.java    # Adds shipping weight
│                   └── services/                          # Business logic services
│                       ├── CheckoutService.java           # Handles checkout process
│                       └── ShippingService.java           # Calculates shipping costs
├── docs/                                                  # Diagrams
│   ├── fawryEcommerceBackend.drawio.png
│   └── fawryEcommerceBackend1.drawio.svg
├── README.md   
└── FawryEcommerceSystem.iml
```

## 🔧 Technical Implementation

### Core Components

#### 1. Product Management
- **IProduct Interface**: Defines contract for all product types
- **Product Class**: Base implementation with name, price, and quantity
- **ProductDecorator**: Abstract base for extending product functionality
- **ExpiringProductDecorator**: Adds expiry date functionality with validation
- **ShippingProductDecorator**: Adds weight property for shipping calculations

#### 2. Shopping Cart System
- **Cart Class**: Manages product items and quantities
- Validates product availability and expiry dates
- Supports add, remove, and clear operations
- Prevents adding expired products

#### 3. Customer Management
- **Customer Class**: Handles customer information and balance
- Uses `BigDecimal` for precise monetary calculations
- Implements balance validation and withdrawal functionality

#### 4. Business Services
- **CheckoutService**: Orchestrates the complete checkout process
  - Validates cart contents and product availability
  - Calculates subtotal and applies shipping costs
  - Processes customer payment and updates inventory
  - Generates detailed receipt
- **ShippingService**: Calculates shipping costs based on item weights
  - Cost calculation: 27.2727 per kg
  - Handles only shippable items (implements ShippingItem interface)

## 💻 Usage Examples

### Running the Application
```bash
# Compile the Java files
javac -d out src/main/java/com/ecommerce/*.java src/main/java/com/ecommerce/model/*/*.java src/main/java/com/ecommerce/model/*/*/*.java src/main/java/com/ecommerce/services/*.java

# Run the main class
java -cp out main.java.com.ecommerce.Main
```

### Sample Output
```
Adding Cheese 200.0g (Expires in: 2025-07-14 ) to cart
Adding Biscuits 700.0g to cart
Adding Scratch Card to cart
Shipping items to: Shubra, Cairo, Egypt
** Shipment Notice **
1     x Biscuits                 700.00g
2     x Cheese                   200.00g
Total package weight: 1.1 kg

** Checkout receipt ** 
1     Scratch Card       5.00
1     Biscuits       150.00
2     Cheese         200.00
-------------------------------
Subtotal: 355.0
Shipping cost: 29.99997
Total: 384.99997
```

## 🧪 Demo Scenarios

The `Main.java` class demonstrates several key scenarios:

1. **Successful Purchase**: Customer with sufficient balance buying valid products
2. **Expired Product Handling**: Attempting to add expired milk to cart
3. **Insufficient Balance**: Customer trying to purchase items exceeding their balance
4. **Mixed Product Types**: Combination of expiring, shipping, and regular products

## 🛠️ Key Features Demonstrated

### Error Handling
- **Expired Products**: Prevents adding/purchasing expired items
- **Insufficient Stock**: Validates product availability
- **Insufficient Balance**: Checks customer balance before purchase
- **Invalid Quantities**: Prevents negative or zero quantities

### Business Logic
- **Inventory Management**: Automatic stock reduction after purchase
- **Shipping Calculations**: Weight-based shipping cost calculation
- **Receipt Generation**: Detailed breakdown of purchase items and costs
- **Cart Management**: Flexible add/remove operations with validation

## 🎯 Learning Objectives Achieved

This project demonstrates proficiency in:
- **Object-Oriented Programming**: Encapsulation, inheritance, polymorphism
- **Design Patterns**: Decorator pattern for extending functionality and dynamic inheritance
- **Clean Architecture**: Separation of concerns with service layers
- **Error Handling**: Comprehensive exception handling and validation
- **Data Types**: Proper use of BigDecimal for monetary calculations
- **Interface Design**: Clean contracts between components
- **Business Logic**: Real-world e-commerce workflow implementation
