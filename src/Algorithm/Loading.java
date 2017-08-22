package Algorithm;

import java.util.Scanner;

/**
 * Created by admin on 2017/8/21.
 */
public class Loading {
    private int n;//集装箱的数量
    private int[] w;//集装箱的重量数组
    private int c;//第一艘轮船的载重量
    private int cw;//当前载重量
    private int bestw;//当前最优载重量
    private int r;//剩余集装箱重量
    private int[] x;//当前解
    private int[] bestx;//当前最优解
    public void backtrace(int i){
        if(i>n-1){
            if(cw>bestw) {
                for (int j = 0; j < n; j++) {
                    bestx[j] = x[j];
                }
                bestw = cw;
                return;
            }

        }
        r-=w[i];
        if(cw+w[i]<c){
            x[i]=1;
            cw+=w[i];
            backtrace(i+1);
            cw-=w[i];
        }
        if(cw+r>bestw){
            x[i]=0;
            backtrace(i+1);
        }
        r+=w[i];
    }

    public static void main(String[] args) {
        Loading X=new Loading();
        System.out.println("输入货物的数量");
        Scanner scanner=new Scanner(System.in);
        X.n=scanner.nextInt();
        X.w=new int[X.n];
        X.x=new int[X.n];
        X.bestx=new int[X.n];
        System.out.println("输出货物的重量数组：");
        for(int i=0;i<X.n;i++){
            X.w[i]=(int)(100*Math.random());
            System.out.println(X.w[i]);
        }
        System.out.println("输入第一艘货船的载重量");
        X.c=scanner.nextInt();
        for(int i=0;i<X.n;i++)
            X.r+=X.w[i];
        X.backtrace(0);
        System.out.println("输出当前最优解集");
        for(int i=0;i<X.n;i++)System.out.println(X.bestx[i]+" ");
        System.out.println();
        System.out.println("最优解: "+X.bestw);
    }
}
