package model;

public class SongNode {
    private final String title;
    private SongNode next;
    private SongNode prev;

    public SongNode(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Song title cannot be empty");
        }
        this.title = title.trim();
    }

    public String getTitle() { return title; }
    public SongNode getNext() { return next; }
    public SongNode getPrev() { return prev; }
    public void setNext(SongNode next) { this.next = next; }
    public void setPrev(SongNode prev) { this.prev = prev; }
}
