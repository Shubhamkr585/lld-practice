import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DebtSimplifier {
    public static Map<String, Map<String, Double>> simplifyDebts(
            Map<String, Map<String, Double>> groupBalances) {

        Map<String, Double> netAmounts = new HashMap<>();

        for (Map.Entry<String, Map<String, Double>> userBalance : groupBalances.entrySet()) {
            netAmounts.put(userBalance.getKey(), 0.0);
        }

        for (Map.Entry<String, Map<String, Double>> userBalance : groupBalances.entrySet()) {
            String creditorId = userBalance.getKey();
            for (Map.Entry<String, Double> balance : userBalance.getValue().entrySet()) {
                String debtorId = balance.getKey();
                double amount = balance.getValue();

                if (amount > 0) {
                    netAmounts.put(creditorId, netAmounts.get(creditorId) + amount);
                    netAmounts.put(debtorId, netAmounts.get(debtorId) - amount);
                }
            }
        }

        List<AbstractMap.SimpleEntry<String, Double>> creditors = new ArrayList<>();
        List<AbstractMap.SimpleEntry<String, Double>> debtors = new ArrayList<>();

        for (Map.Entry<String, Double> net : netAmounts.entrySet()) {
            if (net.getValue() > 0.01) {
                creditors.add(new AbstractMap.SimpleEntry<>(net.getKey(), net.getValue()));
            } else if (net.getValue() < -0.01) {
                debtors.add(new AbstractMap.SimpleEntry<>(net.getKey(), -net.getValue()));
            }
        }

        creditors.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        debtors.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        Map<String, Map<String, Double>> simplifiedBalances = new HashMap<>();

        for (Map.Entry<String, Map<String, Double>> userBalance : groupBalances.entrySet()) {
            simplifiedBalances.put(userBalance.getKey(), new HashMap<>());
        }

        int i = 0, j = 0;
        while (i < creditors.size() && j < debtors.size()) {
            String creditorId = creditors.get(i).getKey();
            String debtorId = debtors.get(j).getKey();
            double creditorAmount = creditors.get(i).getValue();
            double debtorAmount = debtors.get(j).getValue();

            double settleAmount = Math.min(creditorAmount, debtorAmount);

            simplifiedBalances.get(creditorId).put(debtorId, settleAmount);
            simplifiedBalances.get(debtorId).put(creditorId, -settleAmount);

            creditors.get(i).setValue(creditors.get(i).getValue() - settleAmount);
            debtors.get(j).setValue(debtors.get(j).getValue() - settleAmount);

            if (creditors.get(i).getValue() < 0.01) i++;
            if (debtors.get(j).getValue() < 0.01) j++;
        }

        return simplifiedBalances;
    }
}