package Algorithm;

import Node.Node;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by admin on 2017/8/23.
 */
public class BBFlow {
    public int n;//作业数
    public int bestc;//最小完成时间和
    public int[][]m;//各作业所需的处理时间数组
    public int[][]b;//各作业所需的处理时间排序数组
    public int[][]a;//数组m和b的对应关系数组
    public int[] bestx;//最优解
    public boolean[][] y;//工作数组
    public BBFlow(int n,int[][] m){
        this.n=n;
        bestc=Integer.MAX_VALUE;
        this.m=m;
        b=new int[n][2];
        a=new int[n][2];
        bestx=new int[n];
        y=new boolean[n][2];
    }
    public void swap(int[][] b,int i,int j,int k,int t){
        int temp=b[i][j];
        b[i][j]=b[k][t];
        b[k][t]=temp;
    }
    public void swap(int[] x,int i,int j){
        int temp=x[i];
        x[i]=x[j];
        x[j]=temp;
    }
    public void sort(){
        int[] c=new int[n];
        for(int j=0;j<2;j++){
            for(int i=0;i<n;i++){
                b[i][j]=m[i][j];
                c[i]=i;
            }
            for(int i=0;i<n-1;i++){
                for(int k=n-1;k>i;k--){
                    if(b[k][j]<b[k-1][j]){
                        swap(b,k,j,k-1,j);
                        swap(c,k,k-1);
                    }
                }
            }
        for(int i=0;i<n;i++)
            a[c[i]][j]=i;
        }
    }
//计算完成时间和下界
    public int bound(Nodes enode,int[] f){
        for(int k=0;k<n;k++){
            for(int j=0;j<2;j++){
                y[k][j]=false;
            }
        }
        for(int k=0;k<enode.s;k++){
            for(int j=0;j<2;j++){
                y[a[enode.x[k]][j]][j]=true;
            }
        }
        f[1]=enode.f[1]+m[enode.x[enode.s]][0];
        f[2]=((f[1]>enode.f[2])?f[1]:enode.f[2])+m[enode.x[enode.s]][1];
        int sf2=enode.sf2+f[2];
        int s1=0;
        int s2=0;
        int k1=n-enode.s;
        int k2=n-enode.s;
        int f3=f[2];
        //计算f1的值
        for(int j=0;j<n;j++){
            if(!y[j][0]){
                k1--;
                if(k1==n-enode.s-1) f3=(f[2]>f[1]+b[j][0])?f[2]:f[1]+b[j][0];
                s1+=f[1]+k1*b[j][0];
            }
        }
        for(int j=0;j<n;j++){
            if(!y[j][1]){
                k2--;
                s1+=b[j][1];
                s2+=f3+k2*b[j][1];
            }
        }
        return sf2+((s1>s2)?s1:s2);
    }
    public int bbFlow(int nn){
        n=nn;
        sort();
        LinkedList<Nodes> heap=new LinkedList<Nodes>();
        Nodes enode=new Nodes(n);
        do{
         if(enode.s==n){
             if(enode.sf2<bestc){
                 bestc=enode.sf2;
                 for(int i=0;i<n;i++)bestx[i]=enode.x[i];
             }
         }else{
             //产生当前扩展结点的儿子结点
             for(int i=enode.s;i<n;i++){
                 swap(enode.x,enode.s,i);
                 int[]f=new int[3];
                 int bb=bound(enode,f);
                 if(bb<bestc){
                     //子树可能含有最优解
                     //结点插入最小堆
                     Nodes node=new Nodes(enode,f,bb,n);
                     heap.add(node);
                     Collections.sort(heap);
                 }
                 swap(enode.x,enode.s,i);
             }//完成扩展节点
         }//
            enode=heap.poll();
        }while(enode!=null&&enode.s<=n);
        return bestc;
    }

    public static void main(String[] args) {
        int n=3;
        int[][] m={{2,1},{3,1},{2,3}};
        BBFlow f=new BBFlow(n,m);
        f.bbFlow(n);
        System.out.println("最优批处理调度顺序为：");
        for(int i=0;i<n;i++)
            System.out.print((f.bestx[i]+1)+" ");
        System.out.println();
        System.out.println("最优调度所需的最短时间为:"+f.bestc);
    }
}
