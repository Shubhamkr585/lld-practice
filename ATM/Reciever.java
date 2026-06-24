class BankService {
    private double balance = 5000; // Mock balance for demonstration

    public boolean authorize(int pin) {
        return pin == 1234; // Simple mock validation
    }

    public boolean deduct(int amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Bank: Approved. Remaining Balance: " + balance);
            return true;
        }
        System.out.println("Bank: Insufficient funds.");
        return false;
    }

    public void refund(int amount) {
        balance += amount;
        System.out.println("Bank: Transaction rolled back. Balance restored to: " + balance);
    }
}