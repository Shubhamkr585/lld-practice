class ATMSystem {
    private static ATMSystem instance;
    private ATMState currentState;
    private final CashHandler dispenser;
    private final BankService bankService;

    // Defined States
    private final ATMState idleState;
    private final ATMState hasCardState;
    private final ATMState authenticatedState;

    private ATMSystem() {
        bankService = new BankService();

        // Initialize Chain: 2000 -> 500 -> 100
        CashHandler h2000 = new NoteHandler(2000);
        CashHandler h500 = new NoteHandler(500);
        CashHandler h100 = new NoteHandler(100);
        h2000.setNext(h500);
        h500.setNext(h100);
        this.dispenser = h2000; 

        idleState = new IdleState(this);
        hasCardState = new HasCardState(this);
        authenticatedState = new AuthenticatedState(this);
        currentState = idleState;
    }

    public static synchronized ATMSystem getInstance() {
        if (instance == null) instance = new ATMSystem();
        return instance;
    }

    // Getters and Setters
    public void setCurrentState(ATMState state) { this.currentState = state; }
    public ATMState getIdleState() { return idleState; }
    public ATMState getHasCardState() { return hasCardState; }
    public ATMState getAuthenticatedState() { return authenticatedState; }
    public CashHandler getDispenser() { return dispenser; }
    public BankService getBankService() { return bankService; }

    // User Actions
    public void insertCard() { currentState.insertCard(); }
    public void authenticate(int pin) { currentState.insertPin(pin); }
    public void withdraw(int amount) { currentState.requestCash(amount); }
}
