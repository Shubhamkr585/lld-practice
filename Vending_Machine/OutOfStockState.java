class OutOfStockState implements State {

    public void selectItem(VendingMachine vm, int slotId) {
        System.out.println("Item unavailable");
    }

    public void insertMoney(VendingMachine vm, int amount) {
        System.out.println("Cannot process payment");
    }

    public void dispense(VendingMachine vm) {
        System.out.println("Nothing to dispense");
    }
}