package util;

public class Base62Encoder {
    private static final char[] ALPHABET="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final int BASE=ALPHABET.length;

    public String encode(long id){
        if(id<0){
            throw new IllegalArgumentException("id cannot be negative for this base62 encoding");

        }
        StringBuilder sb=new StringBuilder();
        long current =id;
        while(current>0){
            int remainder=(int)(current%BASE);
            sb.append(ALPHABET[remainder]);
            current /= BASE;
        }
        return sb.reverse().toString();
    }
}
