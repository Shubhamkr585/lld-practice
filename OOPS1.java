// 1. ABSTRACT CLASS (Common logic + Blueprint)
abstract class Payment {
    protected String transactionId;

    public Payment(String id) {
        this.transactionId = id;
    }

    // Concrete method (Sabke liye same)
    public void printReceipt() {
        System.out.println("Transaction ID: " + transactionId);
    }

    // Abstract method (Sabka alag hoga) -> C++ ka Pure Virtual Function
    abstract void processAmount(double amount);
}

// 2. INTERFACE (Capability/Feature)
interface Refundable {
    void refund(double amount);
}

// 3. CONCRETE CLASS 1 (Credit Card - Is a Payment, Can be Refunded)
class CreditCard extends Payment implements Refundable {

    public CreditCard(String id) {
        super(id); // Parent constructor call (C++: : Payment(id))
    }

    @Override
    void processAmount(double amount) {
        System.out.println("Processing $" + amount + " via Visa Network...");
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunding $" + amount + " to Card.");
    }
}

// 4. CONCRETE CLASS 2 (Cash - Is a Payment, CANNOT be Refunded online)
class Cash extends Payment {

    public Cash(String id) {
        super(id);
    }

    @Override
    void processAmount(double amount) {
        System.out.println("Collecting $" + amount + " in Cash.");
    }
    // Cash class ne Refundable implement nahi kiya, toh refund() nahi hai.
}

// 5. MAIN (Polymorphism in Action)
public class OOPS1 {
    public static void main(String[] args) {
        // LLD Rule: Reference Parent ka, Object Child ka
        Payment p1 = new CreditCard("TXN_101");
        Payment p2 = new Cash("TXN_102");

        p1.processAmount(500); // Output: Visa Network...
        p2.processAmount(200); // Output: Cash...

        p1.printReceipt(); // Common logic inheritance

        // Refund Check
        // p2.refund(200); // ❌ ERROR! Cash ke paas refund nahi hai.

        // Type Casting (Similiar to dynamic_cast in C++)
        if (p1 instanceof Refundable) {
            ((Refundable) p1).refund(500);
        }
    }
}