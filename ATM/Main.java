public class Main {
    public static void main(String[] args) {
        ATMSystem atm = ATMSystem.getInstance();

        System.out.println("--- Scenario 1: Normal Withdrawal ---");
        atm.insertCard();
        atm.authenticate(1234);
        atm.withdraw(2600);

        System.out.println("\n--- Scenario 2: Action without Card ---");
        atm.withdraw(500);
    }
}