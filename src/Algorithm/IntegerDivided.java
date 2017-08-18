package Algorithm;

/**
 * Created by admin on 2017/8/16.
 */
public class IntegerDivided {
    public static int  result(int n,int m){
        if(n==1||m==1)return 1;
        if(n<m) return result(n,n);
        else if(n==m)return 1+result(n,n-1);
        else return result(n,m-1)+result(n-m,m);

    }
    public static void main(String[] args) {
    System.out.println(IntegerDivided.result(6,6));
    }
}
