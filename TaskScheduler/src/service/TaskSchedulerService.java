package service;

import model.Task;

import java.util.*;

public class TaskSchedulerService {

    private final PriorityQueue<Task> taskQueue;

    private long sequence=0L;

    private final Set<Integer> existingTaskIds=new HashSet<>();

    public TaskSchedulerService(){
        this.taskQueue=new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {

                if(t1.getPriority() != t2.getPriority()){
                    return Integer.compare(t1.getPriority(), t2.getPriority());
                }
                return Long.compare(t1.getCreatedAt(), t2.getCreatedAt());
            }
        });
    }

    public Task addTask(int id, String name, int priority) {
        if (priority <= 0) {
            throw new IllegalArgumentException("Priority must be a positive integer (1 = highest).");
        }
        if(existingTaskIds.contains(id)){
            throw new IllegalArgumentException("Task ID already exists.");
        }

        long createdAt = ++sequence;
        Task task = new Task(id, name, priority, createdAt);
        taskQueue.offer(task);
        existingTaskIds.add(id);
        return task;
    }
    public boolean hasPendingTasks(){
        return !taskQueue.isEmpty();
    }
    public Task getNextTask(){
        return taskQueue.peek();
    }

    public Task executeNextTask(){
        Task next=taskQueue.poll();
        if(next==null){
            return null;
        }
        System.out.println("Executing task: " + next);
        return next;
    }

    public void executeAll(){
        while(!taskQueue.isEmpty()){
            executeNextTask();
        }
    }

    public List<Task> getPendingTasks(){
        List<Task> snapshot=new ArrayList<>(taskQueue);
        snapshot.sort(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if(t1.getPriority()!= t2.getPriority()){
                    return Integer.compare(t1.getPriority(), t2.getPriority());
                }
                return Long.compare(t1.getCreatedAt(), t2.getCreatedAt());
            }
        });
        return snapshot;
    }
}
