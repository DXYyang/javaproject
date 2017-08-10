package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/7.
 */
public class SemaphoreDemo {
    final static int SIZE=25;

    public static void main(String[] args) throws InterruptedException {
        final Pool<Fat> pool=new Pool<Fat>(Fat.class,SIZE);
        ExecutorService exec= Executors.newCachedThreadPool();
        for(int i=0;i<SIZE;i++)exec.execute(new CheckoutTask<Fat>(pool));
        System.out.println("ALL CheckoutTasks created");
        List<Fat> list=new ArrayList<Fat>();
        for(int i=0;i<SIZE;i++){
            Fat f=pool.checkOut();
            System.out.println(i+":main() thread checked out");
            f.operation();
            list.add(f);
        }
        Future<?> blocked=exec.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    pool.checkOut();
                }
                catch (InterruptedException e){
                  System.out.println("checkOut() Interrupted");
                }
            }
        });
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);
        System.out.println("Checking in objects in"+list);
        for(Fat f:list)pool.checkIn(f);
        for(Fat f:list)pool.checkIn(f);
        exec.shutdown();
    }
}
