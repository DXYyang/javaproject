package concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * Created by admin on 2017/8/2.
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t=new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
