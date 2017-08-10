package concurrency;

/**
 * Created by admin on 2017/8/2.
 */
public class Sleeper extends Thread {
    private int duration;
    public Sleeper(String name ,int sleepTime){
        super(name);
        duration=sleepTime;
        start();
    }
    public void run(){
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName()+" was interrupted. "+"is Interrupted:"+isInterrupted());
            return;
        }
        System.out.println(getName()+" has awakened");
    }
}
