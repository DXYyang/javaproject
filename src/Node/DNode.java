package Node;

/**
 * Created by admin on 2017/8/11.
 */
public class DNode<T> {
    public T data;
    public DNode<T>prev,next;
    public DNode(T data,DNode<T> prev,DNode<T> next){
        this.data=data;
        this.prev=prev;
        this.next=next;
    }
    public DNode(T data){
        this(data,null,null);
    }
    public DNode(){
        this(null,null,null);
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
