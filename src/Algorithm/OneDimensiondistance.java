package Algorithm;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by admin on 2017/8/17.
 */
public class OneDimensiondistance {
   static float Random(){
        Random rand=new Random();
        float result=rand.nextFloat()*1000;
        return result;
    }
   static  int input(float s[]){
        int length;
        System.out.println("输入点的个数");
        Scanner in=new Scanner(System.in);
        length=in.nextInt();
        System.out.println("点在X轴坐标上的位置");
        for(int i=0;i<length;i++)
        {
            s[i]=Random();
            System.out.print(s[i]+" ");
        }
        return length;
    }
   static float Max(float s[],int l,int r){
        float s_max=s[l];
        for(int i=l+1;i<=r;i++)
            if(s_max<s[i])s_max=s[i];
        return s_max;
    }
   static float Min(float s[],int l,int r){
        float s_min=s[l];
        for(int i=l+1;i<=r;i++)
            if(s_min>s[i]) s_min=s[i];
        return s_min;
    }
   static void Swap(float a[],int x,int y){
        float temp=a[x];
        a[x]=a[y];
        a[y]=temp;
    }
  static  int Partition(float s[],float x,int l,int r){
        int i=l-1,j=r+1;
        while(true){
            while(s[++i]<x&&i<r);
            while(s[--j]>x);
            if(i>=j)break;
            Swap(s,i,j);
        }
        return j;
    }
   static Pair Cpair(float s[],int l,int r){
       Pair Minpair=new Pair(99999,0,0);
       if(r-l<1)return Minpair;
       float m1=Max(s,l,r),m2=Min(s,l,r);
       float m=(m1+m2)/2;
       int j=Partition(s,m,l,r);
       Pair dl=Cpair(s,l,j),d2=Cpair(s,j+1,r);
       float p=Max(s,l,j),q=Min(s,j+1,r);
       if(dl.d<d2.d){
           if((q-p)<dl.d){
               Minpair.d=q-p;
               Minpair.x1=q;
               Minpair.x2=p;
               return Minpair;
           }else return dl;
       }else {
           if((q-p)<d2.d){
               Minpair.d=q-p;
               Minpair.x1=q;
               Minpair.x2=p;
               return Minpair;
           }
           else return d2;
       }
    }

    public static void main(String[] args) {
        int m;
        float s[]=new float[100];
        Pair d;
        m=input(s);
        d=OneDimensiondistance.Cpair(s,0,m-1);
        System.out.println();
        System.out.print("("+d.x1+" "+d.x2+")");
        System.out.println(d.d);

    }
}
