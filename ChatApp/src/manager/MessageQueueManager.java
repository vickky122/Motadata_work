package manager;

import model.Message;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueueManager {
    private final Queue<Message> queue=new LinkedList<>();

    public synchronized void enqueue(Message m){
        queue.offer(m);
    }
    public synchronized Message dequeue(){
        return queue.poll();
    }
    public synchronized boolean isEmpty(){
        return queue.isEmpty();
    }
}
