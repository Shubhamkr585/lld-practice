// The Command Interface
interface Transaction {
    boolean execute();
    void rollback();
}

// The State Interface
interface ATMState {
    void insertCard();
    void ejectCard();
    void insertPin(int pin);
    void requestCash(int amount);
}

// The Chain of Responsibility Base
abstract class CashHandler {
    protected CashHandler nextHandler;
    public void setNext(CashHandler next) { this.nextHandler = next; }
    public abstract void dispense(int amount);
}