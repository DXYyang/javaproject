package concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/7.
 */
public class TellerManager implements  Runnable {
    private ExecutorService exec;
    private CustomerLine customers;
    private PriorityBlockingQueue<Teller> workingTellers=new PriorityBlockingQueue<Teller>();
    private Queue<Teller>tellersDoingOtherThings=new LinkedList<Teller>();
    private int adjustmentPeriod;
    private static Random rand=new Random(47);
    public TellerManager(ExecutorService e,CustomerLine customers,int adjustmentPeriod){
        exec=e;
        this.customers=customers;
        this.adjustmentPeriod=adjustmentPeriod;
        Teller teller=new Teller(customers);
        exec.execute(teller);
        workingTellers.add(teller);
    }
    public void adjustTellerNumber(){
        if(customers.size()/workingTellers.size()>2){
            if(tellersDoingOtherThings.size()>0){
                Teller teller=tellersDoingOtherThings.remove();
                teller.serveCustomerLine();
                workingTellers.offer(teller);
                return ;
            }
            Teller teller=new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
            return;
        }
        if(workingTellers.size()>1&&customers.size()/workingTellers.size()<2)
            reassignOneTeller();
    }
    private  void reassignOneTeller(){
        Teller teller=workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherThings.offer(teller);
    }
    @Override
    public void run() {
    try{
        while(!Thread.interrupted()){
            TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
            adjustTellerNumber();
            System.out.println(customers+"{");
            for(Teller teller:workingTellers)
                System.out.println(teller.shortString()+" ");
            System.out.println("}");
        }
    }
    catch (InterruptedException e){
        System.out.println(this+"interrupted");
    }
    System.out.println(this+"terminating");
    }

    @Override
    public String toString() {
        return "TellerManager";
    }
}
