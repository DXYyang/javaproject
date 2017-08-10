package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/4.
 */
public class PipedIO {
    public static void main(String[] args) throws Exception{
     Sender sender=new Sender();
     Receiver receiver=new Receiver(sender);
     ExecutorService  exec= Executors.newCachedThreadPool();
     exec.execute(sender);
     exec.execute(receiver);
     TimeUnit.SECONDS.sleep(4);
     exec.shutdownNow();
    }
}
