import java.util.List;

public class Expense {
    public static int nextExpenseId = 0;
    public String expenseId;
    public String description;
    public double totalAmount;
    public String paidByUserId;
    public List<Split> splits;
    public String groupId;
    public SplitStrategy strategy;

    public Expense(String desc, double amount, String paidBy, SplitStrategy strategy, String group) {
        this.expenseId = "expense" + (++nextExpenseId);
        this.description = desc;
        this.totalAmount = amount;
        this.paidByUserId = paidBy;
        this.strategy = strategy;
        this.groupId = group;
    }

    public Expense(String desc, double amount, String paidBy, SplitStrategy strategy) {
        this(desc, amount, paidBy, strategy, "");
    }

    public void calculateSplit(List<String> userIds, List<Double> values) {
        this.splits = strategy.calculateSplit(totalAmount, userIds, values);
    }
}