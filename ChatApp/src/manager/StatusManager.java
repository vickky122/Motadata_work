package manager;

public class StatusManager {
    private  int[] status;
    private static final int DEFAULT_CAPACITY=128;

    public StatusManager(){
        this.status=new int[DEFAULT_CAPACITY];
    }
    private synchronized void ensureCapacity(int userId){
        if(userId< status.length) return;
        int newCap=status.length;
        while(userId>=newCap) newCap*=2;
        int[] newArr = new int[newCap];
        System.arraycopy(status, 0, newArr, 0, status.length);
        status = newArr;
    }
    public void setOnline(int userId){
        ensureCapacity(userId);
        status[userId]=1;
    }

    public void setOffline(int userId){
        if(userId < status.length) status[userId]=0;
    }

    public int getStatus(int userId){
        if(userId < status.length) return status[userId];
        return 0;
    }
}
