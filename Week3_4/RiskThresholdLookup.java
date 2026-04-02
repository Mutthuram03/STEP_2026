import java.util.*;

public class RiskThresholdLookup {

    // ---------------- LINEAR SEARCH ----------------
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear: Found at index " + i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Linear: Not found");
        }

        System.out.println("Comparisons: " + comparisons);
    }

    // ---------------- BINARY SEARCH (FLOOR) ----------------
    public static int floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                System.out.println("Binary comparisons: " + comparisons);
                return arr[mid];
            }

            if (arr[mid] < target) {
                result = arr[mid]; // possible floor
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary comparisons: " + comparisons);
        return result;
    }

    // ---------------- BINARY SEARCH (CEILING) ----------------
    public static int ceiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return arr[mid];
            }

            if (arr[mid] > target) {
                result = arr[mid]; // possible ceiling
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};
        int target = 30;

        // Linear Search
        linearSearch(risks, target);

        // Binary Floor & Ceiling
        int floorVal = floor(risks, target);
        int ceilVal = ceiling(risks, target);

        System.out.println("Floor(" + target + "): " + floorVal);
        System.out.println("Ceiling(" + target + "): " + ceilVal);
    }
}