package Algorithm;

import java.util.Scanner;

/**
 * Created by admin on 2017/8/18.
 */
public class schedule {
   static void Table(int k,int n,int a[][]){
        for(int i=1;i<=n;i++)a[1][i]=i;
        int m=1;
        for(int s=1;s<=k;s++){
            n/=2;
            for(int t=1;t<=n;t++){
                for(int i=m+1;i<=2*m;i++){
                    for(int j=m+1;j<=2*m;j++){
                        a[i][j+(t-1)*m*2]=a[i-m][j+(t-1)*m*2-m];
                        a[i][j+(t-1)*m*2-m]=a[i-m][j+(t-1)*m*2];
                    }
                }
            }
            m*=2;
        }
    }

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("请输入k的值");
        int k=in.nextInt();
        int n=1;
        for(int i=1;i<=k;i++)n*=2;
        int a[][]=new int[n+1][n+1];
        schedule.Table(k,n,a);
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
    }
}
