package concurrency;

/**
 * Created by admin on 2017/8/3.
 */
public class PairManager1 extends PairManager {
    @Override
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}
