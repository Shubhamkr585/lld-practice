class HasMoneyState implements State{

    public void selectItem(VendingMachine vm,int slotId){

        System.out.println("Item already selected");

    }

    public void insertMoney(VendingMachine vm,int amount){
        vm.balance+=amount;
        Slot s=vm.inventory.get(vm.selectedSlot);
        if(vm.balance>=s.item.price){
            System.out.println(StateType.DISPENSING);
        }
        else{
            System.out.println("Insufficient balance,add more");
        }
    }

    public void dispense(VendingMachine vm){

        System.out.println("Select item first");

    }
}