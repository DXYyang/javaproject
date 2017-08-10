package concurrency.CarInstall;

import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/10.
 */
public class ChassisBuilder implements Runnable {
    private CarQueue carQueue;
    private  int counter=0;

    public ChassisBuilder(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
     try{
         while(!Thread.interrupted()){
             TimeUnit.MILLISECONDS.sleep(500);
             Car c=new Car(counter++);
             System.out.println("ChassisBuilder created"+c );
             carQueue.put(c);
         }
     }
     catch (InterruptedException e){
      System.out.println("Interrupted:ChassisBuilder");
     }
     System.out.println("ChassisBuilder off");
    }
}
