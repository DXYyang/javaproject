package Algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by admin on 2017/8/23.
 */
public class BBKnapsack {
    public double c;//背包容量
    public int n;//物品总数
    public double[] w;//物品重量数组
    public double[] p;//物品价值数组
    public double cw;//当前重量
    public double cp;//当前价值
    public int[] bestx;//最优解
    public LinkedList<HeapNode> heap;//活节点优先队列
    public double bound(int i){
        double cleft=c-cw;//剩余容量
        double b=cp;
        while(i<=n&&w[i]<=cleft){
            cleft-=w[i];
            b+=p[i];
            i++;
        }
        if(i<=n)b+=p[i]/w[i]*cleft;
        return b;
    }
    public void addLiveNode(double up,double pp,double ww,int lev,BBnode par,boolean ch){
        BBnode b=new BBnode(par,ch);
        HeapNode node=new HeapNode(b,up,pp,ww,lev);
        heap.add(node);
        Collections.sort(heap);
    }
    public double bbKnapsack(){
        BBnode enode=null;
        int i=1;
        double bestp=0.0;//当前最优解
        double up=bound(1);
        while(i!=n+1) {
            double wt = cw + w[i];
            if (wt <= c) {
                if (cp + p[i] > bestp) bestp = cp + p[i];
                addLiveNode(up, cp + p[i], cw + w[i], i + 1, enode, true);
            }
            up = bound(i + 1);
            if (up >= bestp) addLiveNode(up, cp, cw, i + 1, enode, false);
            HeapNode node = heap.poll();
            enode = node.liveNode;
            cw = node.weight;
            cp = node.profit;
            up = node.upperProfit;
            i = node.level;
        }
        for(int j=n;j>0;j--){
            bestx[j]=(enode.leftChild)?1:0;
            enode=enode.parent;
        }
        return cp;
    }
   public double knapsack(double[] pp,double[] ww,double cc,int[] xx){
        c=cc;
        n=pp.length-1;
        double ps=0;//统计所有背包的价值总量
        double ws=0;//统计所有背包的重量之和
       Element[] q=new Element[n];
       for(int i=1;i<=n;i++){
           q[i-1]=new Element(i,pp[i]/ww[i]);
           ps+=pp[i];
           ws+=ww[i];
       }
       if(ws<=c){
           for(int i=1;i<=n;i++)xx[i]=1;
           return ps;
       }
     Arrays.sort(q);
       p=new double[n+1];
       w=new double[n+1];
       for(int i=1;i<=n;i++){
           p[i]=pp[q[i-1].id];
           w[i]=ww[q[i-1].id];
       }
       cw=0;
       cp=0;
       bestx=new int[n+1];
       heap=new LinkedList<HeapNode>();
       double maxp=bbKnapsack();
       for(int i=1;i<=n;i++){
           xx[q[i-1].id]=bestx[i];
       }
       return maxp;
   }

    public static void main(String[] args) {
        double pp[]={0,2,1,4,3};
        double ww[]={0,1,4,2,3};
        double cc=8;
        int n=pp.length-1;
        int[] xx=new int[n+1];
        BBKnapsack b=new BBKnapsack();
        double maxp=b.knapsack(pp,ww,cc,xx);
        System.out.println("装入背包中物品的最大值为:"+maxp);
        System.out.println("装入的物品序号为：");
        for(int i=1;i<=n;i++)
            System.out.println(i+":"+xx[i]);
    }
}
