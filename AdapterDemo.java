// =======================
// Target (Expected Interface)
// =======================
interface PaymentProcessor {
    void pay(int amount);
}

// =======================
//  Adaptee (Incompatible Class)
// =======================
class StripeGateway {
    public void makePayment(int dollars) {
        System.out.println("Stripe processed payment of $" + dollars);
    }
}

// =======================
//  Adapter (Bridge)
// =======================
class StripeAdapter implements PaymentProcessor {

    private StripeGateway stripe;

    public StripeAdapter(StripeGateway stripe) {
        this.stripe = stripe;
    }

    @Override
    public void pay(int amount) {
        // Translate call
        stripe.makePayment(amount);
    }
}

// =======================
// Client
// =======================
class CheckoutService {

    private PaymentProcessor paymentProcessor;

    public CheckoutService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void checkout(int amount) {
        paymentProcessor.pay(amount);
    }
}

// =======================
// Main
// =======================
public class AdapterDemo {
    public static void main(String[] args) {

        StripeGateway stripe = new StripeGateway();

        PaymentProcessor adapter =
                new StripeAdapter(stripe);

        CheckoutService checkout =
                new CheckoutService(adapter);

        checkout.checkout(100);
    }
}