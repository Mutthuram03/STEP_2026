import java.util.*;

public class AutocompleteSystem {

    HashMap<String, Integer> queries = new HashMap<>();

    public void addQuery(String query) {
        queries.put(query, queries.getOrDefault(query, 0) + 1);
    }

    public void search(String prefix) {

        System.out.println("Suggestions:");

        for (String q : queries.keySet()) {
            if (q.startsWith(prefix)) {
                System.out.println(q + " (" + queries.get(q) + ")");
            }
        }
    }

    public static void main(String[] args) {

        AutocompleteSystem system = new AutocompleteSystem();

        system.addQuery("java tutorial");
        system.addQuery("javascript guide");
        system.addQuery("java download");
        system.addQuery("java tutorial");

        system.search("jav");
    }
}