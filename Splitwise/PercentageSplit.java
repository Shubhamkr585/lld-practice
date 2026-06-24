import java.util.ArrayList;
import java.util.List;

public class PercentageSplit implements SplitStrategy {
    @Override
    public List<Split> calculateSplit(double totalAmount, List<String> userIds, List<Double> values) {
        List<Split> splits = new ArrayList<>();

        if (values == null || values.size() != userIds.size()) {
            throw new IllegalArgumentException("Percentage split values must match users size");
        }

        for (int i = 0; i < userIds.size(); i++) {
            double amount = (totalAmount * values.get(i)) / 100.0;
            splits.add(new Split(userIds.get(i), amount));
        }
        return splits;
    }
}