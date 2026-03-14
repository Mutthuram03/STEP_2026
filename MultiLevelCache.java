import java.util.*;

public class MultiLevelCache {

    HashMap<String, String> L1 = new HashMap<>();
    HashMap<String, String> L2 = new HashMap<>();

    public String getVideo(String videoId) {

        if (L1.containsKey(videoId)) {

            return "L1 Cache HIT → " + L1.get(videoId);
        }

        if (L2.containsKey(videoId)) {

            String data = L2.get(videoId);

            L1.put(videoId, data);

            return "L2 Cache HIT → promoted to L1";
        }

        String data = "VideoData";

        L2.put(videoId, data);

        return "Database HIT → added to cache";
    }

    public static void main(String[] args) {

        MultiLevelCache cache = new MultiLevelCache();

        System.out.println(cache.getVideo("video1"));
        System.out.println(cache.getVideo("video1"));
        System.out.println(cache.getVideo("video2"));
    }
}