package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/3.
 */
public class Waxoff implements Runnable {
    private Car car;
    public Waxoff(Car c){
        car=c;
    }
    @Override
    public void run() {
    while(!Thread.interrupted()){
        try {
            car.waitForWaxing();
            System.out.println("Wax off");
            TimeUnit.MILLISECONDS.sleep(200);
            car.buffed();
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
    }
    System.out.println("Ending Wax off task");
    }
}
