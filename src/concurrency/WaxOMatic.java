package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/3.
 */
public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        Car car=new Car();
        ExecutorService exec= Executors.newCachedThreadPool();
        exec.execute(new Waxoff(car));
        exec.execute(new Waxon(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
