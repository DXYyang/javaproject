package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by admin on 2017/8/7.
 */
public class PriorityBlockingQueueDemo {
    public static void main(String[] args) {
        Random rand=new Random(47);
        ExecutorService exec= Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue=new PriorityBlockingQueue<Runnable>();
        exec.execute(new PrioritizedTaskProducer(queue,exec));
        exec.execute(new PrioritizedTaskConsumer(queue));
    }
}
