package Algorithm;

/**
 * Created by admin on 2017/8/23.
 */
public class Element implements Comparable {
    int id;//背包编号
    double d;//单位重量价值

    public Element(int id, double d) {
        this.id = id;
        this.d = d;
    }

    @Override
    public int compareTo(Object o) {
        double xs=((Element)o).d;
        if(d>xs)return -1;
        if(d==xs)return 0;
        return 1;
    }
    public boolean equals(Object o){
        return d==((Element)o).d;
    }

}
