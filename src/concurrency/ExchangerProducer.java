package concurrency;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by admin on 2017/8/7.
 */
public class ExchangerProducer<T> implements Runnable {
    private Generator<T> generator;
    private Exchanger<List<T>>exchanger;
    private List<T> holder;
    ExchangerProducer(Exchanger<List<T>> exchg,Generator<T> gen,List<T> holder){
        exchanger=exchg;
        generator=gen;
        this.holder=holder;
    }
    @Override
    public void run() {
     try{
         while(!Thread.interrupted()){
             for (int i=0;i<ExchangerDemo.size;i++)
                 holder.add(generator.next());
             holder=exchanger.exchange(holder);
         }
     }
     catch (InterruptedException e){}
    }
}
