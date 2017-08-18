package Algorithm;

/**
 * Created by admin on 2017/8/16.
 */
public class Ackerman {
    public static int result(int n,int m) {
        if (n == 1 && m == 0) return 2;
        if (n == 0 && m >= 0) return 1;
        if (n >= 2 && m == 0) return n + 2;
        return result(result(n - 1, m), m - 1);
    }

    public static void main(String[] args) {

        System.out.println(Ackerman.result(6,1));
    }
}
