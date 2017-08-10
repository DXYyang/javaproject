package concurrency.CarInstall;

import java.util.concurrent.BrokenBarrierException;

/**
 * Created by admin on 2017/8/10.
 */
public abstract class Robot implements Runnable {
   private RobotPool pool;
   public Robot(RobotPool p){pool=p;}
   protected Assembler assembler;
   public Robot assignAssembler(Assembler assembler){
       this.assembler=assembler;
       return this;
   }
   private  boolean engage=false;
   public synchronized void engage(){
       engage=true;
       notifyAll();
   }
   abstract protected void performService();
   public void run(){
       try{
           powerDown();
           while(!Thread.interrupted()){
               performService();
               try {
                   assembler.barrier().await();
               } catch (BrokenBarrierException e) {
                   e.printStackTrace();
               }
           powerDown();
           }
       }
       catch (InterruptedException e){
        System.out.println("Exiting "+this+" via interrupt");
       }
       System.out.println(this+" off");
   }
   private synchronized void powerDown()throws InterruptedException{
       engage=false;
       assembler=null;
       pool.release(this);
       while(engage==false)wait();
   }
   public String toString(){
       return getClass().getName();
   }

}
