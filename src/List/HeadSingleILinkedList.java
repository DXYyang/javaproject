package List;

import Node.Node;
import com.sun.deploy.security.MSCryptoDSASignature;

/**
 * Created by admin on 2017/8/1.
 */
public class HeadSingleILinkedList<T> implements ILinkedList<T> {
    protected Node<T> headNode;
    protected Node<T> rear;
    public HeadSingleILinkedList(){
        this.headNode=rear=new Node<T>(null);
    }
    public HeadSingleILinkedList(Node<T> headNode){
        this();
        this.headNode.next=rear.next=headNode;
        rear=rear.next;
    }
    public HeadSingleILinkedList(T[] array){
        this();
        if(array!=null&&array.length>0){
            this.headNode.next=new Node<T>(array[0]);
            rear=this.headNode.next;
            int i=1;
            while(i<array.length){
                rear.next=new Node<T>(array[i++]);
                rear=rear.next;
            }
        }
    }
    public HeadSingleILinkedList(HeadSingleILinkedList<T> list){
        this();
        if(list!=null&&list.headNode.next!=null){
            this.headNode.next=new Node<T>(list.headNode.data);
            Node<T> p=list.headNode.next;
            rear=this.headNode.next;
            while (p!=null){
                rear.next=new Node<T>(p.data);
                rear=rear.next;
                p=p.next;
            }
        }
    }
    @Override
    public boolean isEmpty() {
        return this.headNode.next==null;
    }

    @Override
    public int length() {
        int length=0;
        Node<T> currentNode=headNode.next;
        while(currentNode!=null){
            length++;
            currentNode=currentNode.next;
        }
        return length;
    }

    @Override
    public T get(int index) {
        if(index>=0){
            int j=0;
            Node<T> pre=this.headNode.next;
            while(pre!=null&&j<index){
                pre=pre.next;
                j++;
            }
            if(pre!=null) return pre.data;
        }
        return null;
    }

    @Override
    public T set(int index, T data) {
        if(index>=0&&data!=null){
            Node<T> pre=this.headNode.next;
            int j=0;
            while(pre!=null&&j<index){
                pre=pre.next;
                j++;
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
        if(data==null) throw new NullPointerException("data can't be empty");
        if(index<0) throw new NullPointerException("index can't be negative");
        int j=0;
        Node<T> pre=this.headNode.next;
        while(pre.next!=null&&j<index-1){
            pre=pre.next;
            j++;
        }
        Node<T> q=new Node<T>(data,pre.next);
        pre.next=q;
        if(pre==this.rear)this.rear=q;
        return true;
    }

    @Override
    public boolean add(T data) {
        if(data==null) throw new NullPointerException("data can't be empty");
        this.rear.next=new Node<T>(data);
        this.rear=this.rear.next;
        return true;
    }

    @Override
    public T remove(int index) {
        T old=null;
        if(index>=0){
            Node<T> pre=this.headNode;
            int j=0;
            while (pre!=null&&j<index-1){
                pre=pre.next;
                j++;
            }
            Node<T> r=pre.next;
            if(r!=null){
                old=r.data;
                if(r==this.rear) this.rear=pre;
            pre.next=r.next;
            }
        }
        return old;
    }

    @Override
    public boolean removeAll(T data) {
        boolean isRemove=false;
        if(data!=null){
            Node<T> front=this.headNode;
            Node<T> pre=front.next;
            while(pre!=null){
                if(data.equals(pre.data)){
                    if(data.equals(rear.data)) this.rear=front;
                    front.next=pre.next;
                    pre=front.next;
                    isRemove=true;
                }else{
                    front=pre;
                    pre=pre.next;
                }
            }
        }
        return isRemove;
    }

    @Override
    public void clear() {
      this.headNode.next=null;
      this.rear=this.headNode;
    }

    @Override
    public boolean contains(T data) {
        if(data!=null){
            Node<T> pre=this.headNode.next;
            while(pre!=null){
                if(data.equals(pre.data))  return true;
                pre=pre.next;
            }
        }
        return false;
    }
    public void concat(HeadSingleILinkedList<T> list){
        if(this.headNode.next==null) this.headNode.next=list.headNode.next;
        else {
            Node<T> pre=this.headNode.next;
            while(pre.next!=null) pre=pre.next;
            pre.next=list.headNode.next;
            this.rear=list.rear;
        }
    }

    @Override
    public String toString() {
        String str="(";
        Node<T> pre=this.headNode.next;
        while(pre!=null){
            str+=pre.data;
            pre=pre.next;
            if(pre!=null) str+=", ";
        }
        return str+")";
    }

    public static void main(String[] args) {
        String[]letters={"A","B","C","D","E","F"};
        HeadSingleILinkedList<String> list=new HeadSingleILinkedList<String>(letters);
        System.out.println(list.add(2,"Y"));
        System.out.println(list.add("G"));
        System.out.println(list.toString());
    }
}
