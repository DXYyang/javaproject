package concurrency;

import java.util.concurrent.DelayQueue;

/**
 * Created by admin on 2017/8/4.
 */
public class DelayedTaskConsumer implements Runnable {
     private DelayQueue<DelayedTask>q;
     public DelayedTaskConsumer(DelayQueue<DelayedTask>q){
         this.q=q;
     }
    @Override
    public void run() {
    try{
        while(!Thread.interrupted())
            q.take().run();
    }catch (InterruptedException e){
    }
    System.out.println("Finished DelayedTaskConsumer");
    }
}
