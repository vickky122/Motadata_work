package repository;

public interface UrlRepository {
    void save(String shortUrl, String longUrl);
    String findLongUrl(String shortUrl);
    boolean existsShortUrl(String shortUrl);

}
