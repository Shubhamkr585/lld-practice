class DispensingState implements State{

    public void selectItem(VendingMachine vm, int sloId){
        System.out.println("Item already dispensing");
    }

    public void insertMoney(VendingMachine vm,int slotId){
        System.out.println("You have paid it before");
    }

    public void dispense(VendingMachine vm){
        Slot s = vm.inventory.get(vm.selectedSlot);

        s.quantity--;
        int change = vm.balance - s.item.price;

        System.out.println("Dispensed: " + s.item.name);
        System.out.println("Change returned: " + change);

        vm.balance = 0;
        vm.selectedSlot = -1;
        vm.setState(StateType.IDLE);
    }

}