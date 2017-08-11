package List;

import Node.DNode;
import concurrency.Pair;

/**
 * Created by admin on 2017/8/11.
 */
public class HeadDoubleILinkedList<T>implements ILinkedList<T> {
    protected DNode<T> head;
    protected DNode<T> tail;
    public HeadDoubleILinkedList(){
        this.head=this.tail=new DNode<T>();
    }
    public HeadDoubleILinkedList(T[] array){
        this();
        if(array!=null&&array.length>0){
            this.head.next=new DNode<T>(array[0]);
            tail=this.head.next;
            tail.prev=this.head;
            int i=1;
            while(i<array.length){
                tail.next=new DNode<T>(array[i++]);
                tail.next.prev=tail;
                tail=tail.next;
            }
        }
    }
    @Override
    public boolean isEmpty() {
        return  head.next==null;
    }

    @Override
    public int length() {
        int length=0;
        DNode<T>pre=head.next;
        while(pre!=null){
            length++;
            pre=pre.next;
        }
        return length;
    }

    @Override
    public T get(int index) {
        if(index>=0){
            int j=0;
            DNode<T> pre=this.head.next;
            while(pre!=null&&j<index){
                j++;
                pre=pre.next;
            }
            if(pre!=null) return pre.data;
        }
        return null;
    }

    @Override
    public T set(int index, T data) {
        T old=null;
        if(index>0&&data!=null){
            int j=0;
            DNode<T> pre=this.head.next;
            while(pre!=null&&j<index){
                j++;
                pre=pre.next;
            }
            if(pre!=null){
                old=pre.data;
                pre.data=data;
            }
        }
        return old;
    }

    @Override
    public boolean add(int index, T data) {
        if(index<0||data==null) throw new NullPointerException("index<0||data==null");
        int j=0;
        DNode<T>front=this.head;
        while(front.next!=null&&j<index){
            j++;
            front=front.next;
        }
        DNode<T> q=new DNode<T>(data,front,front.next);
        if(front.next!=null){
            front.next.prev=q;
        }
        front.next=q;
        if(front==this.tail) this.tail=q;
        return true;
    }

    @Override
    public boolean add(T data) {
        if(data==null)return false;
        DNode<T>q=new DNode<T>(data,tail,null);
        tail.next=q;
        this.tail=q;
        return true;
    }

    @Override
    public T remove(int index) {
        int size=length();
        T temp=null;
        if(index<0||index>=size||isEmpty()) return temp;
        DNode<T> p=this.head.next;
        int j=0;
        while(p!=null&&j<index){
            p=p.next;
            j++;
        }
        if(p.next!=null)p.next.prev=p.prev;
        p.prev.next=p.next;
        if(p==tail) this.tail=p.prev;
        temp=p.data;
        return temp;

    }

    @Override
    public boolean removeAll(T data) {
        boolean isRemove=false;
        if(data==null||isEmpty())
            return isRemove;
        DNode<T>p=this.head.next;
        while(p!=null) {
            if (data.equals(p.data)) {
                if (p == this.tail) {
                    this.tail = p.prev;
                    p.prev = null;
                    this.tail.next = null;
                } else {
                    p.prev.next = p.next;
                    p.next.prev = p.prev;
                }
                isRemove = true;
                p = p.next;
            } else {
                p = p.next;
            }
        }
        return isRemove;
    }

    @Override
    public void clear() {
       this.head.next=null;
       this.tail=this.head;
    }

    @Override
    public boolean contains(T data) {
       if(data==null)return false;
       DNode<T> p=this.head.next;
       while(p!=null){
           if(data.equals(p.data))return true;
           else p=p.next;
       }
       return false;
    }
    public String toString(){
        String str="(";
        DNode<T> pre=this.head.next;
        while(pre!=null){
            str+=pre.data;
            pre=pre.next;
            if(pre!=null)str+=", ";
        }
        return str+")";
    }

    public static void main(String[] args) {
        String[]letters={"A","B","C","D","E","F"};
        HeadDoubleILinkedList<String> list=new HeadDoubleILinkedList<>(letters);
        list.add("Z");
        list.add(3,"Z");
        System.out.println(list.toString());
        list.removeAll("Z");
        System.out.println(list.toString());
    }
}
