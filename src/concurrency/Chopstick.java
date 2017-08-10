package concurrency;

/**
 * Created by admin on 2017/8/4.
 */
public class Chopstick {
    static int ChopstickCount=4;
    private  boolean taken=false;
    public  synchronized void stop()throws InterruptedException{
        while (--ChopstickCount==0) wait();
    }
    public synchronized void release()throws InterruptedException{
        ChopstickCount++;
        notifyAll();
    }
    public synchronized void take() throws InterruptedException{
        while(taken) wait();
        taken=true;
    }
    public synchronized void drop(){
        taken=false;
        notifyAll();
    }
}
