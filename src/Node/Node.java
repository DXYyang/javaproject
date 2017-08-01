package Node;

/**
 * Created by admin on 2017/7/31.
 */
public class Node<T> {
    public T data;//数据域
    public Node<T> next;//地址域
    public Node (){
    }
    public Node(T data){
        this.data=data;
    }

    public Node(T data,Node<T> next){
        this.data=data;
        this.next=next;
    }
}
