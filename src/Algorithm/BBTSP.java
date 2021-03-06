package Algorithm;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by admin on 2017/8/23.
 */
public class BBTSP {
    float[][] a;//图G的邻接矩阵
    public BBTSP(float[][] a){
        this.a=a;
    }
    public static class HeapNode implements Comparable{
        float lcost;//子树费用的下界
        float cc;//当前费用
        float rcost;//x[s:n-1]中顶点最小出边费用总和
        int s;//根节点到当前节点的路径为x[0:s]
        int[] x;//需要进一步搜索的顶点是x[s+1:n-1]

        public HeapNode(float lcost, float cc, float rcost, int s, int[] x) {
            this.lcost = lcost;
            this.cc = cc;
            this.rcost = rcost;
            this.s = s;
            this.x = x;
        }

        @Override
        public int compareTo(Object o) {
            float xlc=((HeapNode)o).lcost;
            if(lcost<xlc) return -1;
            if(lcost==xlc) return 0;
            return 1;
        }
    }
    public float bbTsp(int[] v){
        int n=v.length-1;
        LinkedList<HeapNode> heap=new LinkedList<HeapNode>();
        float[] minOut=new float[n+1];
        float minSum=0;//最小出边费用和
        for(int i=1;i<=n;i++){//针对每个节点，找到最小出边
        float min=Float.MAX_VALUE;
        for(int j=1;j<=n;j++){
            if(a[i][j]<Float.MAX_VALUE&&a[i][j]<min&&a[i][j]!=-1)
                min=a[i][j];
        }
        if(min==Float.MAX_VALUE)return Float.MAX_VALUE;
            minOut[i]=min;
            minSum+=min;
        }
        int[] x=new int[n];
        for(int i=0;i<n;i++)x[i]=i+1;
        HeapNode enode=new HeapNode(0,0,minSum,0,x);
        float bestc=Float.MAX_VALUE;
        while(enode!=null&&enode.s<n-1){
            //非叶节点
            x=enode.x;
             if(enode.s==n-2){
                 //当前扩展节点是叶节点的父节点
                 if(a[x[n-2]][x[n-1]]!=-1&&a[x[n-1]][1]!=-1&&enode.cc+a[x[n-2]][x[n-1]]+a[x[n-1]][1]<bestc){
                     bestc=enode.cc+a[x[n-2]][x[n-1]]+a[x[n-1]][1];
                     enode.cc=bestc;
                     enode.lcost=bestc;
                     enode.s++;
                     heap.add(enode);
                     Collections.sort(heap);
                 }
             }else{//内部节点
                 //产生当前扩展节点的儿子节点
                 for(int i=enode.s+1;i<n;i++){
                     if(a[x[enode.s]][x[i]]!=-1){
                         float cc=enode.cc+a[x[enode.s]][x[i]];
                         float rcost=enode.rcost-minOut[x[enode.s]];
                         float b=cc+rcost;//下界
                         if(b<bestc){
                             //子树可能含有最优解，节点插入最小堆
                             int[]xx=new int[n];
                             for(int j=0;j<n;j++)
                                 xx[j]=x[j];
                             xx[enode.s+1]=x[i];
                             xx[i]=x[enode.s+1];
                             HeapNode node=new HeapNode(b,cc,rcost,enode.s+1,xx);
                             heap.add(node);
                             Collections.sort(heap);
                         }
                     }
                 }
             }
             enode=heap.poll();
        }
        for(int i=0;i<n;i++)
            v[i+1]=x[i];
        return bestc;
    }
    public static void main(String[] args) {
        //int n=4;
        //float[][] a={{0,0,0,0,0},{0,-1,30,6,4},{0,30,-1,5,10},{0,6,5,-1,20},{0,4,10,20,-1}};//a下标从1开始，0用来凑数；-1表示不同，1表示连通
        int n=5;
        float[][] a={{0,0,0,0,0,0},{0,-1,5,-1,7,9},{0,5,-1,10,3,6},{0,-1,10,-1,8,-1},{0,7,3,8,-1,4},{0,9,6,-1,4,-1}};//a下标从1开始，0用来凑数；-1表示不同，1表示连通
        BBTSP b=new BBTSP(a);
        int []v=new int[n+1];
        System.out.println("最短回路长为："+b.bbTsp(v));
        System.out.print("最短回路为：");
        for(int i=1;i<=n;i++){
            System.out.print(v[i]+" ");
        }
    }
}

