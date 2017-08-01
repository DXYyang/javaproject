package Tree;

/**
 * Created by admin on 2017/8/1.
 */
public class AVLNode<T extends Comparable> {
    public AVLNode<T> left;
    public AVLNode<T> right;
    public T data;
    public int height;

    public AVLNode(T data) {
        this(null,null,data);
    }

    public AVLNode(AVLNode<T> left, AVLNode<T> right, T data) {
        this(left,right,data,0);
    }

    public AVLNode(AVLNode<T> left, AVLNode<T> right, T data, int height) {
        this.left=left;
        this.right=right;
        this.data=data;
        this.height = height;
    }

}
