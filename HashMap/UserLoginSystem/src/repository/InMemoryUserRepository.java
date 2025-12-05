package repository;

import model.User;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository{
    private final int shardCount=8;
    private final int virtualNodes=100;

    private final ConsistentHashRing ring=new ConsistentHashRing(shardCount,virtualNodes);
    private final ConcurrentHashMap<String, String>[] shards;

    public InMemoryUserRepository(){
        shards=new ConcurrentHashMap[shardCount];
        for(int i=0;i<shardCount;i++){
            shards[i]=new ConcurrentHashMap<>();
        }
    }
    private ConcurrentHashMap<String, String> getShard(String username) {
        int shardId = ring.getShardForKey(username);
        return shards[shardId];
    }

    @Override
    public boolean save(User user){
        ConcurrentHashMap<String, String> shard = getShard(user.getUsername());
        if(shard.containsKey(user.getUsername())) return false;
        shard.put(user.getUsername(), user.getPasswordHash());
        return true;
    }

    @Override
    public User findByUsername(String username){
        ConcurrentHashMap<String, String> shard = getShard(username);
        String hash=shard.get(username);
        if(hash == null) return null;
        return new User(username, hash);
    }

    @Override
    public boolean existsByUsername(String username){
        return getShard(username).containsKey(username);
    }
}
