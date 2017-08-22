package Algorithm;

import java.util.List;

/**
 * Created by admin on 2017/8/21.
 */
public class KnapsackTest {
    public static void main(String[] args) {
        Knapsack[]bags=new Knapsack[]{
                new Knapsack(2,13),new Knapsack(1,10),
                new Knapsack(3,24),new Knapsack(2,15),
                new Knapsack(4,28),new Knapsack(5,33),
                new Knapsack(3,20),new Knapsack(1,8)
        };
        int totalWeight=12;
        KnapsackProblem kp=new KnapsackProblem(bags,totalWeight);
        kp.solve();
        System.out.println("-----最优解-----");
        System.out.println("最优值："+kp.getBestValue());
        System.out.println("最优集：");
        List<Knapsack> best=kp.getBestSolution();
        for(Knapsack item:best)
            System.out.println(item);
        System.out.println("最优值矩阵：");
        int[][] bestValues = kp.getBestValues();
        for (int i=0; i < bestValues.length; i++) {
            for (int j=0; j < bestValues[i].length; j++) {
                System.out.printf("%-5d", bestValues[i][j]);
            }
            System.out.println();
        }
    }
}
