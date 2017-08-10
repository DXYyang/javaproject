package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/10.
 */
public class ReaderWriterListTest {
    ExecutorService exec=Executors.newCachedThreadPool();
    private final static int SIZE=100;
    private static Random rand=new Random(47);
    private ReaderWriterList<Integer> list=new ReaderWriterList<Integer>(SIZE,0);
    private class Writer implements Runnable{

        @Override
        public void run() {
        try{
            for(int i=0;i<20;i++){
                list.set(i,rand.nextInt());
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch (InterruptedException e){
        }
        System.out.println("Writer finished,shutting down");
        exec.shutdownNow();
        }
    }
    private class Reader implements Runnable{

        @Override
        public void run() {
                try{
                    while(!Thread.interrupted()){
                        for(int i=0;i<SIZE;i++){
                         list.get(i);
                         TimeUnit.MILLISECONDS.sleep(1);
                        }
                    }
            }catch (InterruptedException e){

                }
        }
    }
    public ReaderWriterListTest(int readers,int writers){
        for(int i=0;i<readers;i++)
            exec.execute(new Reader());
        for(int i=0;i<writers;i++)
            exec.execute(new Writer());
    }
}
