package concurrency.restaurant;


import concurrency.restaurant.menu.Food;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by admin on 2017/8/8.
 */
public class WaitPerson implements Runnable{
    private static int counter=0;
    private final int id=counter++;
    private final Restaurant restaurant;
    BlockingQueue<Plate> filledOrders=new LinkedBlockingQueue<Plate>();
    public WaitPerson(Restaurant rest){
        restaurant=rest;
    }
    public void placeOrder(Customer cust,Food food){
        try{
            restaurant.orders.put(new Order(cust,this,food));
        }
        catch (InterruptedException e){
         System.out.println(this+"placeOrder interrupted");
        }
    }

    @Override
    public void run() {
      try{
          while(!Thread.interrupted()){
              Plate plate=filledOrders.take();
              System.out.println(this+" received"+plate+" delivering to"+plate.getOrder().getCustomer());
              plate.getOrder().getCustomer().deliver(plate);
          }
      }
      catch (InterruptedException e){
        System.out.println(this+"interrupted");
      }
      System.out.println(this+"off duty");
    }

    @Override
    public String toString() {
        return "WaitPerson"+id+" ";
    }
}
