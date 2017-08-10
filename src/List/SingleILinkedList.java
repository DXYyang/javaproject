package List;

import Node.Node;

/**
 * Created by admin on 2017/8/1.
 */
public class SingleILinkedList<T> implements ILinkedList<T> {
    protected Node<T> head;
    public SingleILinkedList(Node<T> head){
        this.head=head;
    }
    @Override
    public boolean isEmpty() {
        return this.head==null;
    }

    @Override
    public int length() {
        int length=0;
        Node<T> p=head;
        while(p!=null){
            length++;
            p=p.next;
        }
        return length;
    }

    @Override
    public T get(int index) {
        if(this.head!=null&&index>=0){
            int count=0;
            Node<T> p=this.head;
            while(p!=null&&count<index){
                p=p.next;
                count++;
            }
            if(p!=null) return p.data;
        }
        return null;
    }

    @Override
    public T set(int index, T data) {
        if(this.head!=null&&index>=0&&data!=null){
            Node<T> pre=this.head;
            int count=0;
            while(pre!=null&&count<index){
                pre=pre.next;
                count++;
            }
            if(pre!=null){
                T oldData=pre.data;
                pre.data=data;
                return oldData;
            }
        }
        return null;
    }

    @Override
    public boolean add(int index, T data) {
        if(data==null) return false;
        if(this.head==null||index==0){
            this.head=new Node<T>(data,this.head);
        }else{
            int count=0;
            Node<T> front=this.head;
            while (front.next!=null&&count<index-1){
                front=front.next;
                count++;
            }
            front.next=new Node<T>(data,front.next);
        }
        return true;
    }

    @Override
    public boolean add(T data) {
        return false;
    }

    @Override
    public T remove(int index) {
        T old=null;
        if(this.head!=null&&index>=0){
            if(index==0){
                old=this.head.data;
                this.head=this.head.next;
            }else{
                Node<T> front=this.head;
                int count=0;
                while(front.next!=null&&count<index-1){
                    front=front.next;
                    count++;
                }
                if(front.next!=null){
                    old=front.next.data;
                    front.next=front.next.next;
                }
            }
        }
        return old;
    }

    @Override
    public boolean removeAll(T data) {
        return false;
    }

    @Override
    public void clear() {
        this.head=null;
    }

    @Override
    public boolean contains(T data) {
        return false;
    }
}
