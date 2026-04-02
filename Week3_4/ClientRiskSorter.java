import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    public String toString() {
        return name + "(" + riskScore + ")";
    }
}

public class ClientRiskSorter {

    // Bubble Sort (ascending by riskScore)
    public static void bubbleSort(List<Client> list) {
        int n = list.size();
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).riskScore > list.get(j + 1).riskScore) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("Bubble Sort (asc): " + list);
        System.out.println("Swaps: " + swaps);
    }

    // Insertion Sort (descending by riskScore + accountBalance)
    public static void insertionSort(List<Client> list) {
        for (int i = 1; i < list.size(); i++) {
            Client key = list.get(i);
            int j = i - 1;

            while (j >= 0 && compare(list.get(j), key) < 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.println("Insertion Sort (desc): " + list);
    }

    // Comparator: riskScore DESC, then accountBalance DESC
    public static int compare(Client a, Client b) {
        if (a.riskScore != b.riskScore)
            return Integer.compare(a.riskScore, b.riskScore);
        return Double.compare(a.accountBalance, b.accountBalance);
    }

    // Top K risky clients
    public static void topK(List<Client> list, int k) {
        System.out.print("Top " + k + " risks: ");
        for (int i = 0; i < Math.min(k, list.size()); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        List<Client> clients = new ArrayList<>();

        clients.add(new Client("C", 80, 5000));
        clients.add(new Client("A", 20, 2000));
        clients.add(new Client("B", 50, 3000));

        // Clone lists
        List<Client> bubbleList = new ArrayList<>(clients);
        List<Client> insertionList = new ArrayList<>(clients);

        // Bubble Sort
        bubbleSort(bubbleList);

        // Insertion Sort
        insertionSort(insertionList);

        // Top 3 risky clients
        topK(insertionList, 3);
    }
}