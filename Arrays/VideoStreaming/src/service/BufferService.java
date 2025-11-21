package service;

import buffer.Buffer;
import java.util.List;

public class BufferService {

    private final Buffer buffer;

    public BufferService(Buffer buffer) {
        this.buffer = buffer;
    }

    public void addPacket(int packet) { buffer.add(packet); }

    public int getPacket(int index) { return buffer.get(index); }

    public boolean removePacket() { return buffer.removeLast(); }

    public int getSize() { return buffer.size(); }

    public int getCapacity() { return buffer.capacity(); }

    public List<Integer> listPackets() { return buffer.getAll(); }

    public boolean isIndexValid(int index) {
        return index >= 0 && index < buffer.size();
    }
}
