package Algorithm;

import java.util.Arrays;

/**
 * Created by admin on 2017/8/21.
 */
public class KnapStack2 {
    public static class Element implements Comparable{
          int id;
          double d;

        public Element(int id, double d) {
            this.id = id;
            this.d = d;
        }

        @Override
        public int compareTo(Object o) {
            double xd=((Element)o).d;
            if(d>xd) return -1;
            if(d==xd)return 0;
            return 1;
        }
    }
    double c;//背包容量
    int n;//物品数量
    double w[];//物品重量数组
    double p[];//物品价值数组
    double cw;//当前重量
    double cp;//当前价值
    double bestp;//最优价值
    int[] x;//当前装入背包顺序
    int[] bestx;//最优装入背包顺序
    Element[] q;//q为单位重量价值数组
    public double KnapStack(double[] pp,double[] ww,double cc){
        c=cc;
        n=pp.length;
        cw=0;
        cp=0;
        bestp=0;
        x=new int[n];
        bestx=new int[n];
        q=new Element[n];
        for(int i=0;i<n;i++)
            q[i]=new Element(i,pp[i]/ww[i]);
        Arrays.sort(q);
        for(Element item:q)System.out.println(item.d);
        p=new double[n];
        w=new double[n];
        for(int i=0;i<n;i++){
            p[i]=pp[q[i].id];
            w[i]=ww[q[i].id];
        }
        backtrack(0);
      return bestp;
    }
    public void backtrack(int i){
        if(i>n-1){
            bestp=cp;
            for(int j=0;j<n;j++)
                bestx[j]=x[j];
            return;
        }
       if(cw+w[i]<=c){
            x[i]=1;
           cw+=w[i];
           cp+=p[i];
           backtrack(i+1);
           cw-=w[i];
           cp-=p[i];
       }
       if(bound(i+1)>bestp){
           x[i]=0;
           backtrack(i+1);
       }
    }
     public double bound(int i){
        double cleft=c-cw;
        double bound=cp;
        while(i<n&&w[i]<=cleft){
            cleft-=w[i];
            bound+=p[i];
            i++;
        }
        if(i<n) bound+=p[i]/w[i]*cleft;
        return bound;
     }

    public static void main(String[] args) {
        double[] weight={7,3,4,5};
        double[] price={42,12,40,25};
        double cc=10;
        KnapStack2 knapStack2=new KnapStack2();
        double best=knapStack2.KnapStack(price,weight,cc);
        System.out.println("最优值："+best);
        System.out.println("选中的物品编号分别为：");
        for(int i=0;i< knapStack2.bestx.length;i++){
            if(knapStack2.bestx[i]==1)
                System.out.print(knapStack2.q[i].id+1+" ");
        }
    }
}
