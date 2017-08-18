package Algorithm;

/**
 * Created by admin on 2017/8/16.
 */
public class Hanoi {
    public static void move(char a,char b){
        System.out.println("move"+a+"->"+b);
    }
    public static void hanoi(int n,char a,char b,char c){
        if(n>0){
            hanoi(n-1,a,c,b);
            move(a,b);
            hanoi(n-1,c,b,a);
        }
    }

    public static void main(String[] args) {
        Hanoi.hanoi(3,'A','B','C');
    }
}
