package repository;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUrlRepository implements UrlRepository{
    private final ConcurrentHashMap<String,String> storage=new ConcurrentHashMap<>();

    @Override
    public void save(String shortUrl, String longUrl) {
        storage.put(shortUrl, longUrl);
    }

    @Override
    public String findLongUrl(String shortUrl) {
        return storage.get(shortUrl);
    }

    @Override
    public boolean existsShortUrl(String shortUrl) {
        return storage.containsKey(shortUrl);
    }
}
