class IdleState implements ATMState {
    private final ATMSystem atm;
    public IdleState(ATMSystem atm) { this.atm = atm; }

    public void insertCard() {
        System.out.println("Card Accepted.");
        atm.setCurrentState(atm.getHasCardState());
    }
    public void ejectCard() { System.out.println("No card to eject."); }
    public void insertPin(int pin) { System.out.println("Insert card first."); }
    public void requestCash(int amount) { System.out.println("Insert card first."); }
}

class HasCardState implements ATMState {
    private final ATMSystem atm;
    public HasCardState(ATMSystem atm) { this.atm = atm; }

    public void insertCard() { System.out.println("Card already inserted."); }
    public void ejectCard() {
        System.out.println("Card Ejected.");
        atm.setCurrentState(atm.getIdleState());
    }
    public void insertPin(int pin) {
        if (atm.getBankService().authorize(pin)) {
            System.out.println("PIN Correct.");
            atm.setCurrentState(atm.getAuthenticatedState());
        } else {
            System.out.println("Wrong PIN.");
        }
    }
    public void requestCash(int amount) { System.out.println("Enter PIN first."); }
}

class AuthenticatedState implements ATMState {
    private final ATMSystem atm;
    public AuthenticatedState(ATMSystem atm) { this.atm = atm; }

    public void insertCard() { System.out.println("Card already inserted."); }
    public void ejectCard() {
        System.out.println("Card Ejected.");
        atm.setCurrentState(atm.getIdleState());
    }
    public void insertPin(int pin) { System.out.println("Already authenticated."); }

    public void requestCash(int amount) {
        Transaction withdraw = new WithdrawCommand(atm.getBankService(), atm.getDispenser(), amount);
        if (withdraw.execute()) {
            System.out.println("Transaction Successful.");
            ejectCard();
        } else {
            System.out.println("Transaction Failed.");
        }
    }
}
