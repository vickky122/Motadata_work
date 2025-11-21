package playlist;

import model.SongNode;
import java.util.ArrayList;
import java.util.List;

public class PlaylistImpl implements PlaylistOperations {

    private SongNode head;
    private SongNode tail;
    private int size;

    @Override
    public boolean addAtBeginning(String title) {
        SongNode node = new SongNode(title);

        if (head == null) {
            head = tail = node;
        } else {
            node.setNext(head);
            head.setPrev(node);
            head = node;
        }
        size++;
        return true;
    }

    @Override
    public boolean addAtEnd(String title) {
        SongNode node = new SongNode(title);

        if (head == null) {
            head = tail = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }
        size++;
        return true;
    }

    @Override
    public boolean deleteSong(String title) {
        if (head == null) return false;

        SongNode curr = head;
        while (curr != null) {
            if (curr.getTitle().equalsIgnoreCase(title)) {

                if (curr == head) {
                    head = curr.getNext();
                    if (head != null) head.setPrev(null);
                } else if (curr == tail) {
                    tail = curr.getPrev();
                    if (tail != null) tail.setNext(null);
                } else {
                    curr.getPrev().setNext(curr.getNext());
                    curr.getNext().setPrev(curr.getPrev());
                }

                curr.setNext(null);
                curr.setPrev(null);
                size--;
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    @Override
    public List<String> getForwardList() {
        List<String> list = new ArrayList<>();
        SongNode temp = head;
        while (temp != null) {
            list.add(temp.getTitle());
            temp = temp.getNext();
        }
        return list;
    }

    @Override
    public List<String> getBackwardList() {
        List<String> list = new ArrayList<>();
        SongNode temp = tail;
        while (temp != null) {
            list.add(temp.getTitle());
            temp = temp.getPrev();
        }
        return list;
    }

    @Override
    public int size() {
        return size;
    }
}
