package concurrency;

import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by admin on 2017/8/7.
 */
public class PrioritizedTaskConsumer implements Runnable {
    private PriorityBlockingQueue<Runnable> q;

    public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
        this.q = q;
    }

    @Override
    public void run() {
      try{
          while(!Thread.interrupted()) q.take().run();
      }
      catch (InterruptedException e){}
      System.out.print("Finished PrioritizedTaskConsumer");
    }
}
