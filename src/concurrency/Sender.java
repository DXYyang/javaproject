package concurrency;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2017/8/4.
 */
public class Sender implements Runnable {
    private Random rand=new Random(47);
    private PipedWriter out=new PipedWriter();
    public PipedWriter getPipedWriter(){return out;}
    @Override
    public void run() {
        try{
            while (true){
                for(char c='A';c<='z';c++){
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
            }
        }
        catch (InterruptedException e){
            System.out.println(e+"Sender sleep interrupted");
        } catch (IOException e) {
            System.out.println(e+"Sender write exception");
        }
    }
}
