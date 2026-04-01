import java.util.*;

public class TwoSumTransactions {

    public static void findTwoSum(int[] transactions, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < transactions.length; i++) {

            int complement = target - transactions[i];

            if (map.containsKey(complement)) {

                System.out.println("Fraud pair found: "
                        + complement + " + " + transactions[i] +
                        " = " + target);

                return;
            }

            map.put(transactions[i], i);
        }

        System.out.println("No matching transactions found");
    }

    public static void main(String[] args) {

        int[] transactions = {500, 300, 200, 700};

        int target = 500;

        findTwoSum(transactions, target);
    }
}