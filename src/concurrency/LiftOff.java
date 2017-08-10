package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/2.
 */
public class LiftOff implements Runnable {
    protected int countDown=10;
    private static int taskCount=0;
    private final int id=taskCount++;
    public LiftOff(){}
    public LiftOff(int countDown){
        this.countDown=countDown;
    }
    public String status(){
        return "#"+id+"("+(countDown>0?countDown:"Liftoff!")+"), ";
    }
    @Override
    public void run() {
       while(countDown-->0){
           System.out.println(status());
           try {
               TimeUnit.MILLISECONDS.sleep(100);
           } catch (InterruptedException e) {
               System.err.println("Interrupted");
           }
       }
    }

    public static void main(String[] args) {
        ExecutorService exec= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++) exec.execute(new LiftOff());
        exec.shutdown();
    }
}
