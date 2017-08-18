package Algorithm;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by admin on 2017/8/16.
 */
public class Arrangement {
     static void  Swap(int array[], int a,int b) {
        int temp;
        temp=array[a];
        array[a]=array[b];
        array[b]=temp;
    }
  static  void Perm(int array[],int k,int m){
        if(k==m){
            for(int i=0;i<=m;i++)
                System.out.print(array[i]);
            System.out.print('\n');
        }else{
            for(int i=k;i<=m;i++){
                Swap(array,k,i);
                Perm(array,k+1,m);
                Swap(array,k,i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Enter a num");
        Scanner in =new Scanner(System.in);
        int n=in.nextInt();
        int array[]=new int[n];
        for(int i=0;i<n;i++) array[i]=in.nextInt();
        Arrangement.Perm(array,0,n-1);
    }
}
