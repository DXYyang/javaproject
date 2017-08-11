package List;

import Node.Node;

/**
 * Created by admin on 2017/8/11.
 */
public class CircularHeadSILinkedList<T> implements ILinkedList<T> {
    protected Node<T> head;
    protected Node<T> tail;

    public CircularHeadSILinkedList() {
        this.head=new Node<T>(null);
        this.head.next=head;
        this.tail=head;
    }
    public CircularHeadSILinkedList(T[] array){
        this();
        if(array!=null&&array.length>0){
            this.head.next=new Node<T>(array[0]);
            tail=this.head.next;
            int i=1;
            while(i<array.length)
            {
                tail.next=new Node<T>(array[i++]);
                tail=tail.next;
            }
            tail.next=head;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.head.next==head;
    }

    @Override
    public int length() {
        int length=0;
        Node<T>p=this.head.next;
        while (p!=head){
            p=p.next;
            length++;
        }
        return length;
    }

    @Override
    public T get(int index) {
        if (index >= 0) {
            int j = 0;
            Node<T> pre = this.head.next;
            while (pre != null && j < index) {
                j++;
                pre = pre.next;
            }
            if (pre != null) return pre.data;

        }
        return null;
    }
    @Override
    public T set(int index, T data) {
      if(data==null) return null;
        if(index>=0){
            int j=0;
            Node<T> p=this.head.next;
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
        Node<T>p=this.head;
        while(p.next!=head&&j<index){
            p=p.next;
            j++;
        }
        Node<T>q=new Node<T>(data,p.next);
        p.next=q;
        if(p==tail){
            this.tail=q;
        }
        return true;
    }

    @Override
    public boolean add(T data) {
        if(data==null)return false;
        Node<T> q =new Node<T>(data,this.tail.next);
        this.tail.next=q;
        this.tail=q;
        return true;
    }


    @Override
    public T remove(int index) {
        int size=length();
        if(index<0||index>=size||isEmpty()){
            return null;
        }
        int j=0;
        Node<T> p=this.head;
        while(p.next!=head&&j<index){
            j++;
            p=p.next;
        }
            T old=p.next.data;
            if(tail==p.next) tail=p;
            p.next=p.next.next;
            return old;
    }

    @Override
    public boolean removeAll(T data) {
        boolean isRemove=false;
        if(data==null) return isRemove;
        Node<T> front=this.head;
        Node<T> pre=front.next;
        while(pre!=head){
            if(data.equals(pre.data)){
                if(data.equals(tail.data)){
                    this.tail=front;
                }
                front.next=pre.next;
                pre=front.next;
                isRemove=true;
            }else{
                front=pre;
                pre=pre.next;
            }
        }
        return isRemove;
    }

    @Override
    public void clear() {
      this.head.next=head;
      this.tail=head;
    }

    @Override
    public boolean contains(T data) {
        if(data==null) return false;
        Node<T> p=this.head.next;
        while(p!=head){
            if(data.equals(p.data)){
                return true;
            }
            p=p.next;
        }
        return false;
    }

    @Override
    public String toString() {
        String str="(";
        Node<T> p=this.head.next;
        while(p!=head){
            str+=p.data.toString();
            p=p.next;
            if(p!=head) str+=", ";
        }
        return str +")";
    }

    public static void main(String[] args) {
        String []letters={"A","B","C","D","E","F"};
        CircularHeadSILinkedList<String> list=new CircularHeadSILinkedList<>(letters);
        list.add(0,"Y");
         System.out.println(list.add("H"));
        System.out.println(list.tail.data);
        System.out.println(list.toString());
        list.removeAll("Y");
        System.out.println(list.toString());
    }
}
