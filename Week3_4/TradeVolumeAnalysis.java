import java.util.*;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    public String toString() {
        return id + ":" + volume;
    }
}

public class TradeVolumeAnalysis {

    // ---------------- MERGE SORT (ASC) ----------------
    public static void mergeSort(List<Trade> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);

            merge(list, left, mid, right);
        }
    }

    public static void merge(List<Trade> list, int left, int mid, int right) {
        List<Trade> temp = new ArrayList<>();

        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (list.get(i).volume <= list.get(j).volume) {
                temp.add(list.get(i++));
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

    // ---------------- QUICK SORT (DESC) ----------------
    public static void quickSort(List<Trade> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);

            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    public static int partition(List<Trade> list, int low, int high) {
        int pivot = list.get(high).volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).volume > pivot) { // DESC
                i++;
                Collections.swap(list, i, j);
            }
        }

        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    // ---------------- MERGE TWO SORTED LISTS ----------------
    public static List<Trade> mergeTwoLists(List<Trade> a, List<Trade> b) {
        List<Trade> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < a.size() && j < b.size()) {
            if (a.get(i).volume <= b.get(j).volume) {
                result.add(a.get(i++));
            } else {
                result.add(b.get(j++));
            }
        }

        while (i < a.size()) result.add(a.get(i++));
        while (j < b.size()) result.add(b.get(j++));

        return result;
    }

    // ---------------- TOTAL VOLUME ----------------
    public static int totalVolume(List<Trade> list) {
        int sum = 0;
        for (Trade t : list) {
            sum += t.volume;
        }
        return sum;
    }

    public static void main(String[] args) {

        List<Trade> trades = new ArrayList<>();
        trades.add(new Trade("trade3", 500));
        trades.add(new Trade("trade1", 100));
        trades.add(new Trade("trade2", 300));

        // Merge Sort (ASC)
        List<Trade> mergeList = new ArrayList<>(trades);
        mergeSort(mergeList, 0, mergeList.size() - 1);
        System.out.println("Merge Sort (asc): " + mergeList);

        // Quick Sort (DESC)
        List<Trade> quickList = new ArrayList<>(trades);
        quickSort(quickList, 0, quickList.size() - 1);
        System.out.println("Quick Sort (desc): " + quickList);

        // Merge two sorted lists (example split)
        List<Trade> morning = Arrays.asList(new Trade("trade1", 100), new Trade("trade2", 300));
        List<Trade> afternoon = Arrays.asList(new Trade("trade3", 500));

        List<Trade> merged = mergeTwoLists(morning, afternoon);
        System.out.println("Merged list: " + merged);

        // Total volume
        System.out.println("Total volume: " + totalVolume(merged));
    }
}