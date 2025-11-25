package manager;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserManager {
    private final ConcurrentHashMap<String,Integer> usernameToId=new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, String> idToPasswordHash=new ConcurrentHashMap<>();
    private final AtomicInteger idCounter=new AtomicInteger(0);

    //sha-256 hashing
    private String hash(String raw){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] h = md.digest(raw.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(h);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public synchronized int register(String username,String password){
        if (username == null || username.isBlank() || password == null) return -1;
        if (usernameToId.containsKey(username)) return -1;
        int id = idCounter.incrementAndGet();
        usernameToId.put(username, id);
        idToPasswordHash.put(id, hash(password));
        return id;
    }

    public int authenticate(String username, String password) {
        if (username == null || password == null) return -1;
        Integer id = usernameToId.get(username);
        if (id == null) return -1;
        String stored = idToPasswordHash.get(id);
        if (stored == null) return -1;
        return stored.equals(hash(password)) ? id : -1;
    }

    public boolean userExists(String username) {
        return usernameToId.containsKey(username);
    }

    public Integer getUserId(String username) {
        return usernameToId.get(username);
    }
}
