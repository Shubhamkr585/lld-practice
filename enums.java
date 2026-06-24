// 1. Define the custom Type-Safe Enum
enum OrderStatus {
    PLACED, SHIPPED, DELIVERED
}

class OrderSystemNew {
    // The parameter is strictly bound to the OrderStatus type
    public void updateStatus(OrderStatus status) {
        System.out.println("Status updated to: " + status);
    }
}

public class enums {
    public static void main(String[] args) {
        OrderSystemNew system = new OrderSystemNew();

        // Correct usage
        system.updateStatus(OrderStatus.PLACED);

        // ❌ COMPILE-TIME ERROR: The compiler rejects these instantly
        // system.updateStatus("SHIPPED");       // Error: String cannot be converted to OrderStatus
        // system.updateStatus("DELIVERED_TYPO"); // Error: Unknown type
        // system.updateStatus(5);                // Error: int cannot be converted to OrderStatus
    }
}
