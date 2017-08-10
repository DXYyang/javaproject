package concurrency.restaurant;

import concurrency.SynchronizedBlocked;
import concurrency.restaurant.menu.Course;
import concurrency.restaurant.menu.Food;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by admin on 2017/8/8.
 */
public class Customer implements Runnable {
    private  static int counter=0;
    private final int id=counter++;
    private final WaitPerson waitPerson;
    private SynchronousQueue<Plate> placeSetting=new SynchronousQueue<Plate>();
    public Customer(WaitPerson w){
        waitPerson=w;
    }
    public void deliver(Plate p)throws InterruptedException{
        placeSetting.put(p);
}
    @Override
    public void run() {
    for(Course course:Course.values()){
        Food food=course.randomSelection();
        try{
          waitPerson.placeOrder(this,food);
          System.out.println(this+"eating"+placeSetting.take());
        }catch (InterruptedException e){
         System.out.println(this+"waiting for"+course+"interrupted");
         break;
        }
    }
    System.out.println(this+"finished meal, leaving");
    }
    public String toString(){
        return "Customer"+id+" ";
    }
}
