package concurrency;

/**
 * Created by admin on 2017/8/2.
 */
public abstract class IntGenerator {
    private volatile boolean canceled=false;
    public abstract int next();
    public void cancel(){canceled=true;}
    public boolean isCanceled(){return canceled;}

}
