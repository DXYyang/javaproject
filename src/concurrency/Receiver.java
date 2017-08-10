package concurrency;

import java.io.IOException;
import java.io.PipedReader;

/**
 * Created by admin on 2017/8/4.
 */
public class Receiver implements Runnable {
    private PipedReader in;
    public Receiver(Sender sender)throws IOException{
        in=new PipedReader(sender.getPipedWriter());
    }
    @Override
    public void run() {
      try{
          while(true){
              System.out.println("Read: "+(char)in.read()+", ");
          }
      }
      catch (IOException e){
         System.out.println(e+" Receiver read exception");
      }
    }
}
