package Stack;

import Node.Node;

import java.io.Serializable;

/**
 * Created by admin on 2017/7/31.
 */
public class LinkedStack<T> implements Stack<T> ,Serializable {
    private static final long serialVersionUID = 1911829302658328353L;
    private Node<T> top;
    private int size;

    public LinkedStack(){
        this.top=new Node<T>();
    }

    public int size(){
        return size;
    }


    @Override
    public boolean isEmpty() {
        return top==null || top.data==null;
    }

    @Override
    public void push(T data) {
        if(this.top==null){//调用pop()后top可能为null
            this.top=new Node<T>(data);
        }else if(this.top.data==null){
            this.top.data=data;
        }else {
            Node<T> p=new Node<T>(data,this.top);
            top=p;//更新栈顶
        }
        size++;
    }

    @Override
    public T peek()  {
        return top.data;
    }

    @Override
    public T pop() {
        T data=top.data;
        top=top.next;
        size--;
        return data;
    }

}