package manager;

import model.Message;

import java.util.LinkedList;
import java.util.List;

public class ChatHistoryManager {
    private final LinkedList<Message> history=new LinkedList<>();

    public synchronized void addMessage(Message m){
        history.addLast(m);
    }
    public synchronized void removeLast(){
        if(!history.isEmpty()) history.removeLast();
    }
    public synchronized List<Message> getHistorySnapshot(){
        return List.copyOf(history);
    }
    public synchronized int size(){
        return history.size();
    }
}
