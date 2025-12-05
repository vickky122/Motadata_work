package repository;

import java.security.MessageDigest;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.util.Objects.hash;


public class ConsistentHashRing {
    private final int shardCount;
    private final int virtualNodeCount;

    private final SortedMap<Long,Integer> ring=new TreeMap<>();

    public ConsistentHashRing(int shardCount, int virtualNodeCount){
        if(shardCount<=0 || virtualNodeCount<=0){
            throw new IllegalArgumentException("Shard count and virtual node count must be positive integers.");
        }
        this.shardCount=shardCount;
        this.virtualNodeCount=virtualNodeCount;
        buildRing();
    }

    private void buildRing(){
        for (int shardId=0;shardId<shardCount;shardId++){
            for(int v=0;v<virtualNodeCount;v++){
                long hash=hash("SHARD-"+shardId+"-VNODE-"+v);
                ring.put(hash,shardId);
            }
        }
    }
    public int getShardForKey(String key){
        long h=hash(key);
        SortedMap<Long,Integer> tail=ring.tailMap(h);
        return tail.isEmpty()?ring.get(ring.firstKey()) : ring.get(tail.firstKey());
    }
    private long hash(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] b = md.digest(key.getBytes());
            return (((long)b[0] & 0xff) << 56) |
                    (((long)b[1] & 0xff) << 48) |
                    (((long)b[2] & 0xff) << 40) |
                    (((long)b[3] & 0xff) << 32) |
                    (((long)b[4] & 0xff) << 24) |
                    (((long)b[5] & 0xff) << 16) |
                    (((long)b[6] & 0xff) <<  8) |
                    ((long)b[7] & 0xff);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
