import java.util.*;

public class FlashSaleInventoryManager {

private HashMap<String, Integer> inventory;
private Queue<Integer> waitingList;

public FlashSaleInventoryManager() {
    inventory = new HashMap<>();
    waitingList = new LinkedList<>();
}

public void addProduct(String productId, int stock) {
    inventory.put(productId, stock);
}

public void checkStock(String productId) {
    if (inventory.containsKey(productId)) {
        System.out.println(productId + " stock: " + inventory.get(productId));
    } else {
        System.out.println("Product not found");
    }
}

public void purchaseItem(String productId, int userId) {

    if (!inventory.containsKey(productId)) {
        System.out.println("Product does not exist");
        return;
    }

    int stock = inventory.get(productId);

    if (stock > 0) {
        inventory.put(productId, stock - 1);
        System.out.println("Purchase successful for user " + userId +
                ". Remaining stock: " + (stock - 1));
    } 
    else {
        waitingList.add(userId);
        System.out.println("Out of stock. User " + userId +
                " added to waiting list. Position: " + waitingList.size());
    }
}

public static void main(String[] args) {

    FlashSaleInventoryManager system = new FlashSaleInventoryManager();

    system.addProduct("IPHONE15_256GB", 3);

    system.checkStock("IPHONE15_256GB");

    system.purchaseItem("IPHONE15_256GB", 101);
    system.purchaseItem("IPHONE15_256GB", 102);
    system.purchaseItem("IPHONE15_256GB", 103);
    system.purchaseItem("IPHONE15_256GB", 104);
    system.purchaseItem("IPHONE15_256GB", 105);
}

}
