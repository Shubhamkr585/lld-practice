interface State {
    void selectItem(VendingMachine vm, int slotId);
    void insertMoney(VendingMachine vm, int amount);
    void dispense(VendingMachine vm);
}