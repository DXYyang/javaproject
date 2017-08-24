package Algorithm;

import Node.Node;

/**
 * Created by admin on 2017/8/24.
 */
public class Nodes implements Comparable{
    int s;//已安排作业数
    int sf2;//当前机器2 上的完成时间和
    int bb;//当前完成时间和下界
    int[]f;//f[1]机器1上最后完成时间,f[2]机器2上最后完成时间
    int[]x;//当前作业调度
    public Nodes(int n){
        //最小堆结点初始化
        x=new int[n];
        for(int i=0;i<n;i++)
            x[i]=i;
        s=0;
        f=new int[3];
        f[1]=0;
        f[2]=0;
        sf2=0;
        bb=0;
    }
    public Nodes(Nodes e,int[] ef,int ebb,int n){
        //最小堆新结点
        x=new int[n];
        for(int i=0;i<n;i++)x[i]=e.x[i];
        f=ef;
        sf2=e.sf2+f[2];
        bb=ebb;
        s=e.s+1;
    }
    @Override
    public int compareTo(Object o) {
        int xbb=((Nodes)o).bb;
        if(bb<xbb)return -1;
        if(bb==xbb)return 0;
        return 1;
    }
}
