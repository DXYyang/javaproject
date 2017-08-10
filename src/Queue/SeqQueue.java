package Queue;

import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * Created by admin on 2017/8/1.
 */
public class SeqQueue<T> implements Queue<T>,Serializable {
    private static final long serialVersionUID = -1664818681270068094L;
    private static final int  DEFAULT_SIZE = 10;
    private T elementData[];
    private int front,rear;
    private int size;

    public SeqQueue() {
        elementData=(T[])new Object[DEFAULT_SIZE];
        front=rear=0;
    }
    public SeqQueue(int capacity){
        elementData=(T[])new Object[capacity];
        front=rear=0;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front==rear;
    }

    @Override
    public boolean add(T data) {
        if(this.front==(this.rear+1)%this.elementData.length){
            ensureCapacity(elementData.length*2+1);
        }
        elementData[this.rear]=data;
        this.rear=(this.rear+1)%elementData.length;
        size++;
        return true;
    }
    public void ensureCapacity(int capacity){
        if(capacity<size) return ;
        T[] old=elementData;
        elementData=(T[])new Object[capacity];
        int j=0;
        for(int i=this.front;i!=this.rear;i=(i+1)%old.length){
            elementData[j++]=old[i];
        }
        this.front=0;
        this.rear=j;
    }
    @Override
    public boolean offer(T data) {
        if(data==null) throw new NullPointerException("the data can't be null");
        if(this.front==(this.rear+1)%this.elementData.length){
            throw new IllegalArgumentException("The capacity of SeqQueue has reached its maximum");
        }
        elementData[this.rear]=data;
        this.rear=(this.rear+1)%elementData.length;
        size++;
        return true;
    }

    @Override
    public T peek() {
        return elementData[front];
    }

    @Override
    public T element() {
        if(isEmpty()){
            throw new NoSuchElementException("The SeqQueue is empty");
        }
        return peek();
    }

    @Override
    public T poll() {
        T temp=this.elementData[this.front];
        this.front=(this.front+1)%this.elementData.length;
        size--;
        return temp;
    }

    @Override
    public T remove() {
        if(isEmpty()) throw new NoSuchElementException("The SeqQueue is empty");
        return poll();
    }

    @Override
    public void clearQueue() {
     for(int i=this.front;i!=this.rear;i=(i+1)%elementData.length){
         elementData[i]=null;
     }
     this.front=this.rear=0;
        size=0;
    }
}
