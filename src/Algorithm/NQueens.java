package Algorithm;

/**
 * Created by admin on 2017/8/21.
 */
public class NQueens {
    int[]x;
    int N;
    int sum=0;
    private boolean place(int col){
        for(int i=1;i<col;i++){
            if(Math.abs(col-i)==Math.abs(x[col]-x[i])||x[col]==x[i]){
                return false;
            }
        }
        return true;
    }
    private void backtrace(int t){
        if(t>N){
            for(int i=1;i<=N;i++)
            System.out.print(x[i]);
            System.out.println();
            sum++;
        }else{
            for(int j=1;j<=N;j++){
                x[t]=j;
                if(place(t)){
                    backtrace(t+1);
                }
            }
        }
    }
    public int totalNQueens(int n){
        N=n;
        x=new int[n+1];
        backtrace(1);
        return sum;
    }

    public static void main(String[] args) {
        NQueens n=new NQueens();
        System.out.println(n.totalNQueens(5));
    }
}
