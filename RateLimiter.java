import java.util.*;

public class RateLimiter {

    HashMap<String, Integer> requests = new HashMap<>();
    int limit = 5;

    public void checkRequest(String clientId) {

        int count = requests.getOrDefault(clientId, 0);

        if (count < limit) {
            requests.put(clientId, count + 1);
            System.out.println("Request allowed for " + clientId +
                    ". Remaining: " + (limit - (count + 1)));
        } else {
            System.out.println("Rate limit exceeded for " + clientId);
        }
    }

    public static void main(String[] args) {

        RateLimiter limiter = new RateLimiter();

        for (int i = 1; i <= 7; i++) {
            limiter.checkRequest("client123");
        }
    }
}