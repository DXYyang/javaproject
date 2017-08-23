package Algorithm;

/**
 * Created by admin on 2017/8/23.
 */
public class BBnode {
    BBnode parent;//父节点
    boolean leftChild;//左节点标志

    public BBnode(BBnode parent, boolean leftChild) {
        this.parent = parent;
        this.leftChild = leftChild;
    }

}
