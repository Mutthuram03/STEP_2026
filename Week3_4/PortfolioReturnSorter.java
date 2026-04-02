import java.util.*;

class Asset {
    String name;
    double returnRate;
    double volatility;

    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    public String toString() {
        return name + ":" + returnRate + "%";
    }
}

public class PortfolioReturnSorter {

    // ---------------- MERGE SORT (ASC, STABLE) ----------------
    public static void mergeSort(List<Asset> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);

            merge(list, left, mid, right);
        }
    }

    public static void merge(List<Asset> list, int left, int mid, int right) {
        List<Asset> temp = new ArrayList<>();

        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (list.get(i).returnRate <= list.get(j).returnRate) {
                temp.add(list.get(i++)); // stable
            } else {
                temp.add(list.get(j++));
            }
        }

        while (i <= mid) temp.add(list.get(i++));
        while (j <= right) temp.add(list.get(j++));

        for (int k = 0; k < temp.size(); k++) {
            list.set(left + k, temp.get(k));
        }
    }

    // ---------------- QUICK SORT (DESC + VOLATILITY ASC) ----------------
    public static void quickSort(List<Asset> list, int low, int high) {
        if (low < high) {
            int pi = partitionMedianOfThree(list, low, high);

            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    // Median-of-3 pivot selection
    public static int partitionMedianOfThree(List<Asset> list, int low, int high) {
        int mid = (low + high) / 2;

        // Sort low, mid, high
        if (compare(list.get(low), list.get(mid)) < 0)
            Collections.swap(list, low, mid);
        if (compare(list.get(low), list.get(high)) < 0)
            Collections.swap(list, low, high);
        if (compare(list.get(mid), list.get(high)) < 0)
            Collections.swap(list, mid, high);

        // Use mid as pivot
        Collections.swap(list, mid, high);
        return partition(list, low, high);
    }

    // Partition
    public static int partition(List<Asset> list, int low, int high) {
        Asset pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(list.get(j), pivot) > 0) { // DESC logic
                i++;
                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    // Comparator: returnRate DESC, volatility ASC
    public static int compare(Asset a, Asset b) {
        if (a.returnRate != b.returnRate)
            return Double.compare(a.returnRate, b.returnRate);
        return Double.compare(b.volatility, a.volatility);
    }

    public static void main(String[] args) {

        List<Asset> assets = new ArrayList<>();

        assets.add(new Asset("AAPL", 12, 5));
        assets.add(new Asset("TSLA", 8, 7));
        assets.add(new Asset("GOOG", 15, 4));

        // Merge Sort (ASC)
        List<Asset> mergeList = new ArrayList<>(assets);
        mergeSort(mergeList, 0, mergeList.size() - 1);
        System.out.println("Merge Sort (asc): " + mergeList);

        // Quick Sort (DESC + volatility ASC)
        List<Asset> quickList = new ArrayList<>(assets);
        quickSort(quickList, 0, quickList.size() - 1);
        System.out.println("Quick Sort (desc): " + quickList);
    }
}