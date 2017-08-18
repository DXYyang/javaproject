package Algorithm;

import reflect.SelectingMethods;

import java.util.Random;

/**
 * Created by admin on 2017/8/17.
 */
public class SelectByMiddle {
    static void Swap(int array[],int a,int b){
         int temp=array[a];
         array[a]=array[b];
         array[b]=temp;
    }
  static  void BubbleSort(int a[],int p,int r){
        boolean exchange;
        for(int i=r-1;i>=p;i--){
            exchange=false;
            for(int j=p;j<=i;j++)
            {
                if(a[j]>a[j+1]){
                    Swap(a,j,j+1);
                    exchange=true;
                }
            }
            if(false==exchange) break;
        }
    }
    static int Partition(int a[],int p,int r,int x){
        int i=p-1,j=r+1;
        while(true){
            while(a[++i]<x&&i<r);
            while(a[--j]>x);
            if(i>=j)break;
            Swap(a,i,j);
        }
        return j;
    }
static int Select(int a[],int p,int r,int k){
        if(r-p<75){
            BubbleSort(a,p,r);

            return a[p+k-1];
        }
        for(int i=0;i<=(r-p-4)/5;i++){
            BubbleSort(a,p+5*i,p+5*i+4);
            Swap(a,p+5*i+2,p+i);
        }
        int x=Select(a,p,p+(r-p-4)/5,(r-p-4)/10);
        int i=Partition(a,p,r,x);
        int j=i-p+1;
        if(k<=j)return Select(a,p,i,k);
        else return Select(a,i+1,r,k-j);
    }

    public static void main(String[] args) {
        int a[]=new int[500];
        Random rand=new Random();
        for (int i=0;i<500;i++)
            a[i]=rand.nextInt(500);
        System.out.print( SelectByMiddle.Select(a,0,499,250));

    }
}
