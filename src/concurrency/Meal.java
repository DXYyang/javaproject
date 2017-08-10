package concurrency;

/**
 * Created by admin on 2017/8/3.
 */
public class Meal {
    private final int orderNum;
    public Meal(int orderNum){
        this.orderNum=orderNum;
    }

    @Override
    public String toString() {
        return "Meal "+orderNum;
    }
}
