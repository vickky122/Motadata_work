package service;

import model.Task;

import java.util.*;

public class TaskSchedulerService {

    private final int shardCount;

    private final List<PriorityQueue<Task>> shards;

    private long sequence=0L;

    private final Set<Integer> existingTaskIds=new HashSet<>();

    private final Comparator<Task> taskComparator=(t1,t2)->{
        if(t1.getPriority() != t2.getPriority()){
            return Integer.compare(t1.getPriority(), t2.getPriority());
        }
        return Long.compare(t1.getCreatedAt(), t2.getCreatedAt());
    };

    public TaskSchedulerService(){
        this(8);
    }
    public TaskSchedulerService(int shardCount){
        if(shardCount<=0){
            throw new IllegalArgumentException("Shard count must be a positive integer.");
        }
        this.shardCount=shardCount;
        this.shards=new ArrayList<>(shardCount);
        for(int i=0;i<shardCount;i++){
            shards.add(new PriorityQueue<>(taskComparator));
        }

    }

    public Task addTask(int id, String name, int priority) {
        if (priority <= 0) {
            throw new IllegalArgumentException("Priority must be a positive integer (1 = highest).");
        }
        if(existingTaskIds.contains(id)){
            throw new IllegalArgumentException("Task ID already exists.");
        }
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("Task name cannot be null or blank.");
        }

        long createdAt = ++sequence;
        Task task = new Task(id, name, priority, createdAt);
        int shardIndex=selectShard(id);

        PriorityQueue<Task> shardQueue = shards.get(shardIndex);
        shardQueue.offer(task);
        existingTaskIds.add(id);
        return task;
    }
    public boolean hasPendingTasks(){
        for(PriorityQueue<Task> shard:shards){
            if(!shard.isEmpty()){
                return true;
            }

        }
        return false;
    }
    public Task getNextTask(){
        Task best =null;
        for(PriorityQueue<Task> shard:shards){
            Task head=shard.peek();
            if(head==null){
                continue;
            }if(best==null || taskComparator.compare(head,best)<0){
                best=head;
            }
        }
        return best;
    }

    public Task executeNextTask(){
        int bestShardIdx=findBestShardIndex();
        if(bestShardIdx==-1){
            return null;
        }
        PriorityQueue<Task> shard=shards.get(bestShardIdx);
        Task next=shard.poll();
        if (next != null) {
            existingTaskIds.remove(next.getId());
            System.out.println("Executing task: " + next);
        }
        return next;
    }

    public void executeAll(){
        while(hasPendingTasks()){
            executeNextTask();
        }
    }

    public List<Task> getPendingTasks(){
        List<Task> all=new ArrayList<>();
        for(PriorityQueue<Task> shard:shards){
            all.addAll(shard);
        }
        all.sort(taskComparator);
        return all;
    }

    private int selectShard(int taskId){
        int hash=taskId &0x7FFFFFFF;
        return hash % shardCount;
    }

    private int findBestShardIndex(){
        int bestIndex=-1;
        Task bestTask=null;
        for(int i=0;i<shardCount;i++){
            PriorityQueue<Task> shard=shards.get(i);
            Task head=shard.peek();
            if(head==null){
                continue;
            }
            if(bestTask==null || taskComparator.compare(head,bestTask)<0){
                bestTask=head;
                bestIndex=i;

            }
        }
        return bestIndex;
    }
}
