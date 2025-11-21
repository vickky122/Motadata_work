package repository;

import model.User;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository{
    private final ConcurrentHashMap<String, String> store=new ConcurrentHashMap<>();

    @Override
    public boolean save(User user){
        if(store.containsKey(user.getUsername())) return false;
        store.put(user.getUsername(), user.getPasswordHash());
        return true;
    }

    @Override
    public User findByUsername(String username){
        String hash=store.get(username);
        if(hash == null) return null;
        return new User(username, hash);
    }

    @Override
    public boolean existsByUsername(String username){
        return store.containsKey(username);
    }
}
