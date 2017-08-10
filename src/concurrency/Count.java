package concurrency;

import java.util.Random;

/**
 * Created by admin on 2017/8/3.
 */
public class Count {
    private int count=0;
    private Random rand=new Random(47);
    public synchronized  int incremnet(){
        int temp=count;
        if(rand.nextBoolean())Thread.yield();
        return (count=++temp);
    }
    public synchronized int value(){
        return count;
    }
}
