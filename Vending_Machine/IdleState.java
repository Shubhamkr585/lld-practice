class IdleState implements State {

    public void selectItem(VendingMachine vm,int slotId){
        Slot s=vm.inventory.get(slotId);
        if(s==null || s.quantity==0) {
            System.out.println("Slot "+slotId+" is empty");
            vm.setState(StateType.OUT_OF_STOCK);
            return;
        }
        vm.selectedSlot=slotId;
        System.out.println("Item selected "+s.item.name);
        vm.setState(StateType.HAS_MONEY);
    }

    public void insertMoney(VendingMachine vm, int amount){
        System.out.println("Select item first");
    }

    public void dispense(VendingMachine vm){
        System.out.println("Select item first");
    }

}