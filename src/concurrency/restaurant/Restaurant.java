package concurrency.restaurant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/8.
 */
public class Restaurant implements Runnable{
    private List<WaitPerson> waitPersons=new ArrayList<WaitPerson>();
    private List<Chef> chefs=new ArrayList<Chef>();
    private ExecutorService exec;
    private static Random rand=new Random(47);
    BlockingQueue<Order> orders=new LinkedBlockingQueue<Order>();
    public Restaurant(ExecutorService e,int nWaitPersons,int nChefs){
        exec=e;
        for(int i=0;i<nWaitPersons;i++){
            WaitPerson waitPerson=new WaitPerson(this);
            waitPersons.add(waitPerson);
            exec.execute(waitPerson);
        }
        for(int i=0;i<nChefs;i++){
            Chef chef=new Chef(this);
            chefs.add(chef);
            exec.execute(chef);
        }
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                WaitPerson wp=waitPersons.get(rand.nextInt(waitPersons.size()));
                Customer c=new Customer(wp);
                exec.execute(c);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }
        catch(InterruptedException e){
         System.out.println("Restaurant interrupted");
        }
        System.out.println("Restaurant closing");
    }
}
