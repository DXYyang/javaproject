package concurrency;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/2.
 */
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {
    public DaemonThreadPoolExecutor(){
        super(0,Integer.MAX_VALUE,60L,
                TimeUnit.SECONDS,new SynchronousQueue<Runnable>(),new DaemonThreadFactory());
    }
}
