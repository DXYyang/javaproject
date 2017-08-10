package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/3.
 */
public class CriticalSection {
    static void testApproaches(PairManager pman1,PairManager pman2) throws InterruptedException {
        ExecutorService exec= Executors.newCachedThreadPool();
        PairManipulator pm1=new PairManipulator(pman1);
        PairManipulator pm2=new PairManipulator(pman2);
        PairChecker
                pcheck1=new PairChecker(pman1),
                pcheck2=new PairChecker(pman2);
        exec.execute(pm1);
        exec.execute(pm2);
        exec.execute(pcheck1);
        exec.execute(pcheck2);
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("pm1: "+pm1+"\npm2: "+pm2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager pman1=new PairManager1(),
                pman2=new PairManager2();
        try {
            testApproaches(pman1,pman2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}