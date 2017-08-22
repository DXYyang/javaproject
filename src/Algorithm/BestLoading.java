package Algorithm;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by admin on 2017/8/22.
 */
public class BestLoading {
    LinkedList<HeapNode> heap;
    public static class BBnode{
        BBnode parent;//父节点
        boolean leftChild;//左儿子节点标志

        public BBnode(BBnode parent, boolean leftChild) {
            this.parent = parent;
            this.leftChild = leftChild;
        }

    }
    public static class HeapNode implements Comparable{
       BBnode liveNode;
       int uweight;//活节点优先级（上界）从根节点到x的所有重量和加上剩余集装箱的重量
        int level;//活节点在子集树中所处的层序号

        public HeapNode(BBnode liveNode, int uweight, int level) {
            this.liveNode = liveNode;
            this.uweight = uweight;
            this.level = level;
        }

        @Override
        public int compareTo(Object o) {
            int xu=((HeapNode)o).uweight;
            if(uweight<xu)return -1;
            if(uweight==xu)return 0;
            return 1;
        }
        public boolean equals(Object x){
            return uweight==((HeapNode)x).uweight;
        }
    }
    public void addLiveNode(int up,int lev,BBnode par,boolean ch){
        BBnode b=new BBnode(par,ch);
        HeapNode node=new HeapNode(b,up,lev);
        heap.add(node);
        Collections.sort(heap);
    }
    public int maxLoading(int[] w,int c,int[] bestx){
        heap=new LinkedList<HeapNode>();
        int n=w.length-1;
        BBnode e=null;//当前扩展节点
        int i=1;//当前扩展节点所在的层
        int ew=0;
        //定义剩余重量数组r;
        int[]r=new int[n+1];
        for(int j=n-1;j>0;j--)
            r[j]=r[j+1]+w[j+1];
        while(i!=n+1){
            if(ew+w[i]<c){
                addLiveNode(ew+w[i]+r[i],i+1,e,true);
            }
            addLiveNode(ew+r[i],i+1,e,false);
            HeapNode node=heap.pollLast();
            i=node.level;
            e=node.liveNode;
            ew=node.uweight-r[i-1];
        }

          for(int j=n;j>0;j--){
            bestx[j]=(e.leftChild)?1:0;
            System.out.print(bestx[j]+"  ");
            e=e.parent;
          }
          System.out.println();
        return ew;
    }

    public static void main(String[] args) {
        int n=4;
        int c=70;
        int w[]={0,20,10,26,15};
        int[] bestx=new int[n+1];
        BestLoading b=new BestLoading();
        int ew=b.maxLoading(w,c,bestx);
        System.out.println("最优装载重量为:"+ew);
    }
}
