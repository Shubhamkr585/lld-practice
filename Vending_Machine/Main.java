public class Main {
    public static void main(String[] args) {
        //singleton pattern
        VendingMachine vm = VendingMachine.getInstance();

        vm.addItem(1, new Item("Coke", 50), 5);
        vm.addItem(2, new Item("Chips", 30), 2);

        vm.selectItem(1);
        vm.insertMoney(20);
        vm.insertMoney(40);
        vm.dispense();

        vm.selectItem(2);
        vm.insertMoney(30);
        vm.dispense();
    }
}