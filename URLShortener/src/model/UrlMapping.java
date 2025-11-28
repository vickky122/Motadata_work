package model;

public class UrlMapping {
    private final String shortUrl;
    private final String longUrl;

    public UrlMapping(String shortUrl, String longUrl){
        if(shortUrl == null || shortUrl.isBlank()){
            throw new IllegalArgumentException("Short URL cannot be null or blank");
        }
        if(longUrl == null || longUrl.isBlank()){
            throw new IllegalArgumentException("Long URL cannot be null or blank");
        }
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }


    public String getShortUrl() {
        return shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }
}
