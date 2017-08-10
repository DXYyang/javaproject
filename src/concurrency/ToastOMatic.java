package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/4.
 */
public class ToastOMatic {
    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue=new ToastQueue(),
                buttrerQueue=new ToastQueue(),
                finishedQueue=new ToastQueue();
        ExecutorService exec= Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue,buttrerQueue));
        exec.execute(new Jammer(buttrerQueue,finishedQueue));
        exec.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
