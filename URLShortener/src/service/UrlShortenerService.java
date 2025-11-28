package service;

import repository.UrlRepository;
import util.Base62Encoder;
import util.IdGenerator;

public class UrlShortenerService {
    private final UrlRepository urlRepository;
    private final IdGenerator idGenerator;
    private final Base62Encoder base62Encoder;
    private final String baseDomain;

    public UrlShortenerService(UrlRepository urlRepository, IdGenerator idGenerator, Base62Encoder base62Encoder, String baseDomain) {
        if(urlRepository == null || idGenerator==null || base62Encoder==null){
            throw new IllegalArgumentException("All dependencies must be provided");
        }
        if(baseDomain == null || baseDomain.isBlank()){
            throw new IllegalArgumentException("Base domain cannot be null or blank");
        }

        if(!baseDomain.endsWith("/")){
            baseDomain+="/";
        }

        this.urlRepository = urlRepository;
        this.idGenerator = idGenerator;
        this.base62Encoder = base62Encoder;
        this.baseDomain = baseDomain;
    }

    public String createShortUrl(String longUrl){
        validateLongUrl(longUrl);
        long id=idGenerator.nextId();
        String shortUrl=base62Encoder.encode(id);

        urlRepository.save(shortUrl,longUrl);

        return baseDomain+shortUrl;

    }
    private void validateLongUrl(String longUrl){
        if(longUrl == null || longUrl.isBlank()){
            throw new IllegalArgumentException("Long URL cannot be null or blank");
        }
        String trimmed=longUrl.trim().toLowerCase();
        if(!trimmed.startsWith("http://") && !trimmed.startsWith("https://")){
            throw new IllegalArgumentException("Long URL must start with http:// or https://");
        }
    }

    public String getOriginalUrl(String shortUrlorCode){
        if(shortUrlorCode ==null || shortUrlorCode.isBlank()){
            throw new IllegalArgumentException("short url od code can not be blank");
        }

        String shortUrl=extractShortCode(shortUrlorCode);
        String longUrl=urlRepository.findLongUrl(shortUrl);
        if(longUrl==null){
            throw new IllegalArgumentException("Short URL does not exist");
        }
        return longUrl;
    }
    private String extractShortCode(String shortUrlorCode){
        String trimmed=shortUrlorCode.trim();
        if(trimmed.startsWith(baseDomain)){
            return trimmed.substring(baseDomain.length());
        }
        return trimmed;
    }
}
