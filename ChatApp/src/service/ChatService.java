package service;

import manager.*;
import model.Message;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ChatService {
    private final UserManager userManager;
    private final StatusManager statusManager;
    private final ChatHistoryManager chatHistoryManager;
    private final MessageQueueManager messageQueueManager;
    private final UndoManager undoManager;

    private final AtomicInteger messageIdGenerator=new AtomicInteger(0);


    public ChatService(UserManager userManager, StatusManager statusManager, ChatHistoryManager chatHistoryManager, MessageQueueManager messageQueueManager, UndoManager undoManager) {
        this.userManager = userManager;
        this.statusManager = statusManager;
        this.chatHistoryManager = chatHistoryManager;
        this.messageQueueManager = messageQueueManager;
        this.undoManager = undoManager;
    }

    public int register(String username, String password){
        return userManager.register(username,password);
    }
    public int login(String username, String password){
        int uid=userManager.authenticate(username,password);
        if(uid>=0) statusManager.setOnline(uid);
        return uid;
    }
    public void logout(int userId){
        statusManager.setOffline(userId);
    }

    public void sendMessage(int fromuserId, int touserId,String text){
        int mid= messageIdGenerator.incrementAndGet();
        Message m=new Message(mid,fromuserId,touserId,text);
        messageQueueManager.enqueue(m);
        processPendingMessage();
    }

    public void processPendingMessage(){
        while(!messageQueueManager.isEmpty()){
            Message m=messageQueueManager.dequeue();
            if(m==null) break;
            chatHistoryManager.addMessage(m);
            undoManager.push(m);
        }
    }

    public boolean undoLastMessage(){
        Message last=undoManager.pop();
        if(last==null) return false;

        List<Message> hist=chatHistoryManager.getHistorySnapshot();
        if (!hist.isEmpty() && hist.get(hist.size() - 1).getId() == last.getId()) {
            chatHistoryManager.removeLast();
            return true;
        } else {
            return false;
        }
    }

    public List<Message> getHistory(){
        return chatHistoryManager.getHistorySnapshot();
    }
}
