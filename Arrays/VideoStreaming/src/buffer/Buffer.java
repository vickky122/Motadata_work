package buffer;

import java.util.List;

public interface Buffer {
    void add(int value);
    int get(int index);
    int size();
    int capacity();
    boolean removeLast();
    List<Integer> getAll();
}
