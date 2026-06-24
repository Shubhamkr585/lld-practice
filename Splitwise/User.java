import java.util.HashMap;
import java.util.Map;

public class User implements Observer {
    public static int nextUserId = 0;
    public String userId;
    public String name;
    public String email;
    public Map<String, Double> balances;

    public User(String name, String email) {
        this.userId = "user" + (++nextUserId);
        this.name = name;
        this.email = email;
        this.balances = new HashMap<>();
    }

    @Override
    public void update(String message) {
        System.out.println("[NOTIFICATION to " + name + "]: " + message);
    }

    public void updateBalance(String otherUserId, double amount) {
        balances.put(otherUserId, balances.getOrDefault(otherUserId, 0.0) + amount);
        if (Math.abs(balances.get(otherUserId)) < 0.01) {
            balances.remove(otherUserId);
        }
    }

    public double getTotalOwed() {
        double total = 0;
        for (Map.Entry<String, Double> balance : balances.entrySet()) {
            if (balance.getValue() < 0) {
                total += Math.abs(balance.getValue());
            }
        }
        return total;
    }

    public double getTotalOwing() {
        double total = 0;
        for (Map.Entry<String, Double> balance : balances.entrySet()) {
            if (balance.getValue() > 0) {
                total += balance.getValue();
            }
        }
        return total;
    }
}