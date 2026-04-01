import java.util.*;

public class WebsiteAnalytics {

    HashMap<String, Integer> pageViews = new HashMap<>();
    HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    HashMap<String, Integer> trafficSources = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    public void showDashboard() {

        System.out.println("Top Pages:");

        for (String page : pageViews.keySet()) {

            int views = pageViews.get(page);
            int unique = uniqueVisitors.get(page).size();

            System.out.println(page + " → " + views + " views (" + unique + " unique)");
        }

        System.out.println("\nTraffic Sources:");

        for (String src : trafficSources.keySet()) {
            System.out.println(src + " → " + trafficSources.get(src));
        }
    }

    public static void main(String[] args) {

        WebsiteAnalytics analytics = new WebsiteAnalytics();

        analytics.processEvent("/article/breaking-news", "user1", "google");
        analytics.processEvent("/article/breaking-news", "user2", "facebook");
        analytics.processEvent("/sports/championship", "user3", "google");
        analytics.processEvent("/article/breaking-news", "user1", "direct");

        analytics.showDashboard();
    }
}