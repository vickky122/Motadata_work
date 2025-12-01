package model;

public class Task {
    private final int id;
    private final String name;
    private final int priority;
    private final long createdAt;

    public Task(int id, String name, int priority, long createdAt){
        if(name ==null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString(){
        return "Task{id=" + id +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", createdAt=" + createdAt +
                '}';
    }
}
