package Algorithm;

/**
 * Created by admin on 2017/8/21.
 */
public class Knapsack {
    private int weight;
    private int value;

    public Knapsack(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
    public int getWeight(){
        return weight;
    }
    public int getValue(){
        return value;
    }

    @Override
    public String toString() {
        return "Knapsack{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
    }
}
