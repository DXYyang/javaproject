package concurrency;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by admin on 2017/8/7.
 */
public class CustomerLine extends ArrayBlockingQueue<Customer> {
    public CustomerLine(int maxLineSize){
        super(maxLineSize);
    }
    public String toString(){
        if(this.size()==0)return "[Empty]";
        StringBuilder result=new StringBuilder();
        for(Customer customer:this)result.append(customer);
        return result.toString();
    }
}
