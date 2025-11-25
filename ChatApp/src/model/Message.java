package model;

import java.time.Instant;

public class Message {
    private final int id;
    private final int senderId;
    private final int receiverId;
    private final String text;
    private final long timestamp;

    public Message(int id,int senderId,int receiverId,String text){
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.timestamp= Instant.now().toEpochMilli();
    }

    public int getId() {
        return id;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getText() {
        return text;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + id + "] from:" + senderId + " to:" + receiverId + " at:" + timestamp + " => " + text;
    }
}
