package manager;

import model.Message;

import java.util.ArrayDeque;
import java.util.Deque;

public class UndoManager {
    private final Deque<Message> stack= new ArrayDeque<>();

    public synchronized void push(Message m){
        stack.push(m);
    }
    public synchronized Message pop(){
        return stack.isEmpty() ? null:stack.pop();
    }
    public synchronized boolean isEmpty(){
        return stack.isEmpty();
    }
}
