class NoteHandler extends CashHandler {
    private final int denomination;

    public NoteHandler(int denomination) {
        this.denomination = denomination;
    }

    @Override
    public void dispense(int amount) {
        if (amount >= denomination) {
            int notes = amount / denomination;
            int balance = amount % denomination;
            System.out.println("Dispensing " + notes + " notes of ruppee" + denomination);
            if (balance > 0 && nextHandler != null) {
                nextHandler.dispense(balance);
            }
        } else if (nextHandler != null) {
            nextHandler.dispense(amount);
        }
    }
}