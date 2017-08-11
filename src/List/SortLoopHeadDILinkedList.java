package List;

import Node.DNode;

/**
 * Created by admin on 2017/8/11.
 */
public class SortLoopHeadDILinkedList<T extends Comparable<?extends T>>extends LoopHeadDILinkedList<T> {
    public boolean add (T data){
        if(data==null||!(data instanceof Comparable))
            throw new NullPointerException("data can't be null or data instanceof Comparable must be true");
        Comparable cmp=data;
        if(this.isEmpty()||cmp.compareTo(this.head.prev.data)>0){
            return super.add(data);
        }
        DNode<T> p=this.head.next;
        while(p!=head&&cmp.compareTo(p.data)>0){
            p=p.next;
        }
        DNode<T> q=new DNode<T>(data,p.prev,p);
        p.prev.next=q;
        p.prev=q;
        return true;
    }

    public static void main(String[] args) {
        SortLoopHeadDILinkedList<Integer>list=new SortLoopHeadDILinkedList<>();
        list.add(50);
        list.add(40);
        list.add(80);
        list.add(20);
        System.out.println(list.toString());
    }
}
