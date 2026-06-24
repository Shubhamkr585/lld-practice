import java.util.*;

class VendingMachine {

    Map<Integer, Slot> inventory = new HashMap<>();
    Map<StateType, State> states = new HashMap<>();

    State currentState;

    int selectedSlot = -1;
    int balance = 0;

    private static VendingMachine instance;

    private VendingMachine() {
        states.put(StateType.IDLE, new IdleState());
        states.put(StateType.HAS_MONEY, new HasMoneyState());
        states.put(StateType.DISPENSING, new DispensingState());
        states.put(StateType.OUT_OF_STOCK, new OutOfStockState());

        currentState = states.get(StateType.IDLE);
    }

    public static VendingMachine getInstance() {
        if (instance == null) instance = new VendingMachine();
        return instance;
    }

    void setState(StateType type) {
        currentState = states.get(type);
    }

    void selectItem(int slotId) {
        currentState.selectItem(this, slotId);
    }

    void insertMoney(int amount) {
        currentState.insertMoney(this, amount);
    }

    void dispense() {
        currentState.dispense(this);
    }

    void addItem(int slotId, Item item, int qty) {
        inventory.put(slotId, new Slot(item, qty));
    }
}