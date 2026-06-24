class WithdrawCommand implements Transaction {
    private final BankService bank;
    private final CashHandler dispenser;
    private final int amount;

    public WithdrawCommand(BankService bank, CashHandler dispenser, int amount) {
        this.bank = bank;
        this.dispenser = dispenser;
        this.amount = amount;
    }

    @Override
    public boolean execute() {
        if (bank.deduct(amount)) {
            try {
                dispenser.dispense(amount);
                return true;
            } catch (Exception e) {
                rollback(); // If hardware fails, rollback bank transaction
                return false;
            }
        }
        return false;
    }

    @Override
    public void rollback() {
        bank.refund(amount);
    }
}