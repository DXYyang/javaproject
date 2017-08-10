package concurrency;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/2.
 */
public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
      while(true) {
          try {
              TimeUnit.MILLISECONDS.sleep(100);
          } catch (InterruptedException e) {
              System.out.println("sleep() interrupted");
          }
          System.out.println(Thread.currentThread()+" "+this);
      }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<10;i++){
            Thread daemon=new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}
