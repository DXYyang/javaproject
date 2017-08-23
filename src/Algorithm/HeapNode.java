package Algorithm;

/**
 * Created by admin on 2017/8/23.
 */
public class HeapNode implements Comparable {
     BBnode liveNode;//活节点
    double upperProfit;//节点的价值上界
    double profit;//节点对应的价值
    double weight;//节点对应的重量
    int level;//活节点在子集树中所处的层序号

    public HeapNode(BBnode liveNode, double upperProfit, double profit, double weight, int level) {
        this.liveNode = liveNode;
        this.upperProfit = upperProfit;
        this.profit = profit;
        this.weight = weight;
        this.level = level;
    }

    @Override
    public int compareTo(Object o) {
        double xs=((HeapNode)o).upperProfit;
        if(upperProfit>xs)return -1;
        if(upperProfit==xs)return 0;
        return 1;
    }
}
