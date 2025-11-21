package model;

public class TabNode {
    public String title;
    public TabNode next;
    public TabNode prev;

    public TabNode(String title) {
        this.title = title;
    }
}
