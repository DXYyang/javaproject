package concurrency;

import java.sql.Time;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/3.
 */
public class Waxon implements Runnable {
    private Car car;
    public Waxon(Car c){car=c;}
    @Override
    public void run() {
          try{
              while(!Thread.interrupted()){
                  car.waitForBuffing();
                  System.out.println("Wax on! ");
                  TimeUnit.MILLISECONDS.sleep(200);
                  car.waxed();
              }
          }catch (InterruptedException e){
             System.out.println("Exiting via interrupt");
          }
        System.out.println("Ending Wax On task");
    }
}
