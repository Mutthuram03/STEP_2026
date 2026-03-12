import java.util.*;

class DNSEntry {
String ipAddress;
long expiryTime;

DNSEntry(String ipAddress, long ttlMillis) {
    this.ipAddress = ipAddress;
    this.expiryTime = System.currentTimeMillis() + ttlMillis;
}

boolean isExpired() {
    return System.currentTimeMillis() > expiryTime;
}

}

public class DNSCache {

private HashMap<String, DNSEntry> cache;

public DNSCache() {
    cache = new HashMap<>();
}

public String resolve(String domain) {

    if (cache.containsKey(domain)) {

        DNSEntry entry = cache.get(domain);

        if (!entry.isExpired()) {
            return "Cache HIT → " + entry.ipAddress;
        } else {
            cache.remove(domain);
        }
    }

    String newIP = "172.217.14.206"; 
    cache.put(domain, new DNSEntry(newIP, 5000));

    return "Cache MISS → " + newIP;
}

public static void main(String[] args) throws Exception {

    DNSCache dns = new DNSCache();

    System.out.println(dns.resolve("google.com"));
    System.out.println(dns.resolve("google.com"));

    Thread.sleep(6000);

    System.out.println(dns.resolve("google.com"));
}

}
