package Algorithm;

import java.util.Random;

/**
 * Created by admin on 2017/8/16.
 */
public class RandomizedSelect {
   static void Swap(int array[],int a,int b){
        int temp=array[a];
        array[a]=array[b];
        array[b]=temp;
    }
    static int Random(int x,int y){
        Random rand=new Random();
        int ran_num=rand.nextInt(y-x)+x;
        return ran_num;
    }
    static int Partition(int array[],int p,int r){
        int i=p,j=r;
        int x=array[p];
        while(i<j){
            while(i<j&&array[j]>=x)j--;
            if(i<j){
                array[i]=array[j];
                i++;
            }
            while(i<j&&array[i]<x)i++;
            if(i<j){
                array[j]=array[i];
                j--;
            }
        }
        array[i]=x;
        return i;
    }
    static int RandomizedPartition(int a[],int p,int r){
        int i=Random(p,r);
        Swap(a,i,p);
        return Partition(a,p,r);
    }
 static   int RandomizedSelect(int a[],int p,int r,int k){
        if(p==r){
            return a[p];
        }
        int i=RandomizedPartition(a,p,r);
        int j=i-p+1;
        if(k<=j) return RandomizedSelect(a,p,i,k);
        else return RandomizedSelect(a,i+1,r,k-j);
    }

    public static void main(String[] args) {
        int a[]={5,9,3,4,1,6,7};
      System.out.println(RandomizedSelect.RandomizedSelect(a,0,6,1));
    }
}
