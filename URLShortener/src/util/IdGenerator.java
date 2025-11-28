package util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private final AtomicLong counter=new AtomicLong(1L);

    public long nextId(){
        return counter.getAndIncrement();
    }
}
