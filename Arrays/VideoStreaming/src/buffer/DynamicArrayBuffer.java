package buffer;

import java.util.ArrayList;
import java.util.List;

public class DynamicArrayBuffer implements Buffer {

    private int[] array;
    private int size;

    public DynamicArrayBuffer(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be > 0");
        }
        this.array = new int[initialCapacity];
        this.size = 0;
    }

    @Override
    public void add(int value) {
        if (size == array.length) {
            resize();
        }
        array[size++] = value;
    }

    private void resize() {
        int newCapacity = array.length * 2;
        int[] newArray = new int[newCapacity];

        System.arraycopy(array, 0, newArray, 0, array.length);

        array = newArray;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return array[index];
    }

    @Override
    public boolean removeLast() {
        if (size == 0) return false;
        size--;
        return true;
    }

    @Override
    public int size() { return size; }

    @Override
    public int capacity() { return array.length; }

    @Override
    public List<Integer> getAll() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) list.add(array[i]);
        return list;
    }
}
