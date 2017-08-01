import java.util.Comparator;

/**
 * Created by admin on 2017/7/24.
 */
public class NodeIDComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        int j1=Integer.parseInt(((Node)o1).id);
        int j2=Integer.parseInt(((Node)o2).id);
        return (j1<j2?-1:(j1==j2?0:1));
    }
}
