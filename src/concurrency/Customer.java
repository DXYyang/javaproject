package concurrency;

/**
 * Created by admin on 2017/8/7.
 */
public class Customer {
    private final int serviceTime;
    public Customer(int tm){
        serviceTime=tm;
    }
    public int getServiceTime(){
        return serviceTime;
    }
    public String toString(){
        return "["+serviceTime+"]";
    }
}
