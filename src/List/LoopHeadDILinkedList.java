package List;

import Node.DNode;
import Node.Node;
import concurrency.Pair;

/**
 * Created by admin on 2017/8/11.
 */
public class LoopHeadDILinkedList<T> implements ILinkedList<T> {
    protected DNode<T> head;
    public LoopHeadDILinkedList(){
        this.head=new DNode<T>();
        this.head.next=head;
        this.head.prev=head;
    }
    public LoopHeadDILinkedList(T[] array){
        this();
        if(array!=null&&array.length>0){
            DNode<T> p=new DNode<T>(array[0]);
            head.next=p;
            head.prev=p;
            p.prev=head;
            p.next=head;
            int i=1;
            while(i<array.length){
                p.next=new DNode<T>(array[i++],p,head);
                head.prev=p.next;
                p=p.next;
            }
        }
    }
    @Override
    public boolean isEmpty() {
        return this.head.next==head;
    }

    @Override
    public int length() {
        int length=0;
        DNode<T>p=this.head.next;
        while(p!=this.head){
            length++;
            p=p.next;
        }
        return length;
    }

    @Override
    public T get(int index) {
        if(index>=0){
            int j=0;
            DNode<T>p=this.head.next;
            while(p!=head&&j<index){
                j++;
                p=p.next;
            }
            if(p!=head) return p.data;
        }
        return null;
    }

    @Override
    public T set(int index, T data) {
        if(data==null) return null;
        if(index>=0){
            int j=0;
            DNode<T> p=this.head.next;
            while(p!=head&&j<index){
                j++;
                p=p.next;
            }
            if(p!=head){
                T old=p.data;
                p.data=data;
                return old;
            }
        }
        return null;
    }

    @Override
    public boolean add(int index, T data) {
        int size=length();
        if(data==null||index<0||index>=size) return false;
        int j=0;
        DNode<T> p=this.head;
        while(p.next!=head&&j<index){
            p=p.next;
            j++;
        }
        DNode<T> q=new DNode<T>(data,p,p.next);
        p.next.prev=q;
        p.next=q;
        return true;
    }

    @Override
    public boolean add(T data) {
        if(data==null) return false;
        DNode<T>p=new DNode<T>(data,head.prev,head);
        this.head.prev.next=p;
        this.head.prev=p;
        return true;
    }

    @Override
    public T remove(int index) {
        T old=null;
        int size=length();
        if(index<0||index>=size) return old;
            int j=0;
            DNode<T> p=this.head.next;
            while(p!=head&&j<index){
                j++;
                p=p.next;
            }
            if(p!=head){
                old=p.data;
                p.prev.next=p.next;
                p.next.prev=p.prev;
            }
            return old;
    }

    @Override
    public boolean removeAll(T data) {
        boolean isRemove=false;
        if(data==null) return isRemove;
        DNode<T> p=this.head.next;
        while(p!=head){
            if(data.equals(p.data)){
                p.prev.next=p.next;
                p.next.prev=p.prev;
                isRemove=true;
            }
            p=p.next;
        }
        return isRemove;
    }

    @Override
    public void clear() {
      this.head.prev=head;
      this.head.next=head;
    }

    @Override
    public boolean contains(T data) {
        if(data==null) return false;
        DNode<T> p=this.head.next;
        while(p!=head){
         if(data.equals(p.data))return true;
         p=p.next;
        }
        return false;
    }

    @Override
    public String toString() {
        String str="(";
        DNode<T> p=this.head.next;
        while(p!=head){
            str+=p.data.toString();
            p=p.next;
            if(p!=head)str+=", ";
        }
        return str+")";
    }

    public static void main(String[] args) {
        String[] letters={"A","B","C","D","Z","E","F"};
        LoopHeadDILinkedList<String>list=new LoopHeadDILinkedList<>(letters);
        list.remove(3);
        System.out.println(list.toString());

    }
}
