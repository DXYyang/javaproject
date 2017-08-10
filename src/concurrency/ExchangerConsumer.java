package concurrency;

import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by admin on 2017/8/7.
 */
public class ExchangerConsumer<T> implements Runnable {
    private Exchanger<List<T>>exchanger;
    private List<T> holder;
    private volatile T value;
    ExchangerConsumer(Exchanger<List<T>>ex,List<T>holder){
        exchanger=ex;
        this.holder=holder;
    }
    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                holder=exchanger.exchange(holder);
                for(T x:holder){
                    value=x;
                    holder.remove(x);
                }
            }
        }
        catch (InterruptedException e){}
    System.out.println("Final value:"+value);
    }
}
