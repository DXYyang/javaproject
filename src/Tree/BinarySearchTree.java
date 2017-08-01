package Tree;

import Queue.LinkedQueue;
import Stack.LinkedStack;

/**
 * Created by admin on 2017/7/28.
 */
public class BinarySearchTree<T extends Comparable>implements Tree<T> {
     protected BinaryNode<T>  root;
     public BinarySearchTree(){
         root=null;
     }
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return size(root);
    }
     private  int size(BinaryNode<T> subtree){
         if(subtree==null){
             return 0;
         }else{
             return size(subtree.left)+1+size(subtree.right);
         }
     }
    @Override
    public int height() {
        return height(root);
    }

    private int height(BinaryNode<T> subtree) {
        if(subtree==null){
            return 0;
        }else{
            int l=height(subtree.left);
            int r=height(subtree.right);
            return (l>r)?(l+1):(r+1);
        }
    }

    @Override
    public String preOrder() {
        String sb=preOrder(root);
        if(sb.length()>0){
            sb=sb.substring(0,sb.length()-1);
        }
        return sb;
    }
    public String preOrderTraverse(){
         StringBuffer sb=new StringBuffer();
        LinkedStack<BinaryNode<T>> stack=new LinkedStack<BinaryNode<T>>();
        BinaryNode<T> p=this.root;
        while(p!=null||!stack.isEmpty()){
            if(p!=null){
                sb.append(p.data+",");
                stack.push(p);
                p=p.left;
            }else{
                p=stack.pop();
                p=p.right;
            }
        }
        if(sb.length()>0){
            return sb.toString().substring(0,sb.length()-1);
        }else{
            return sb.toString();
        }
    }
    private String preOrder(BinaryNode<T> subtree){
         StringBuffer sb=new StringBuffer();
         if(subtree!=null){
             sb.append(subtree.data+",");
             sb.append(preOrder(subtree.left));
             sb.append(preOrder(subtree.right));
         }
         return sb.toString();
    }

    @Override
    public String inOrder() {
        String sb=inOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }
        return sb;
    }

    /**
     * 中根遍历
     * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
     * @return
     */
    private String inOrder(BinaryNode<T> subtree) {
        StringBuffer sb=new StringBuffer();
        if (subtree!=null) {//递归结束条件
            //先遍历左子树
            sb.append(inOrder(subtree.left));
            //再遍历根结点
            sb.append(subtree.data+",");
            //最后遍历右子树
            sb.append(inOrder(subtree.right));
        }
        return sb.toString();
    }
    public String inOrderTraverse(){
        StringBuffer sb=new StringBuffer();
        LinkedStack<BinaryNode<T>> stack=new LinkedStack<BinaryNode<T>>();
        BinaryNode<T> p=this.root;
        while(p!=null||!stack.isEmpty()){
            while(p!=null){
                stack.push(p);
                p=p.left;
            }
            if(!stack.isEmpty()){
                p=stack.pop();
                sb.append(p.data+",");
                p=p.right;
            }
        }
        if(sb.length()>0){
            return sb.toString().substring(0,sb.length()-1);
        }else{
            return sb.toString();
        }
    }
    @Override
    public String postOrder() {
        String sb=postOrder(root);
        if(sb.length()>0){
            //去掉尾部","号
            sb=sb.substring(0,sb.length()-1);
        }

        return sb;
    }

    /**
     * 后根遍历
     * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
     * @param subtree
     * @return
     */
    private String postOrder(BinaryNode<T> subtree) {
        StringBuffer sb=new StringBuffer();
        if (subtree!=null) {//递归结束条件
            //先遍历左子树
            sb.append(postOrder(subtree.left));

            //再遍历右子树
            sb.append(postOrder(subtree.right));

            //最后遍历根结点
            sb.append(subtree.data+",");
        }
        return sb.toString();
    }
    public String postOrderTraverse(){
        StringBuffer sb=new StringBuffer();
        LinkedStack<BinaryNode<T>> stack=new LinkedStack<BinaryNode<T>>();
        BinaryNode<T> currentNode=this.root;
        BinaryNode<T> prev=this.root;
        while(currentNode!=null||!stack.isEmpty()){
            while(currentNode!=null){
                stack.push(currentNode);
                currentNode=currentNode.left;
            }
            if(!stack.isEmpty()){
                BinaryNode<T> temp=stack.peek().right;
                if(temp==null||temp==prev){
                    currentNode=stack.pop();
                    sb.append(currentNode.data+",");
                    prev=currentNode;
                    currentNode=null;
                }else{
                    currentNode=temp;
                }
            }
        }
        if(sb.length()>0){
            return sb.toString().substring(0,sb.length()-1);
        }else{
            return sb.toString();
        }
    }
    @Override
    public String levelOrder() {
        LinkedQueue<BinaryNode<T>> queue=new LinkedQueue<BinaryNode<T>>();
        StringBuffer sb=new StringBuffer();
        BinaryNode<T> p=this.root;
        while(p!=null){
            sb.append(p.data);
            if(p.left!=null){
                queue.add(p.left);
            }
            if(p.right!=null){
                queue.add(p.right);
            }
            p=queue.poll();
        }
        return sb.toString();
    }

    @Override
    public void insert(T data) {
     if(data==null) throw new RuntimeException("data can't be null");
     root=insert(data,root);
    }
    private BinaryNode<T> insert(T data,BinaryNode<T> p){
      if(p==null){
          p=new BinaryNode<T>(data,null,null);
      }
      int compareResult=data.compareTo(p.data);
      if (compareResult<0){
          p.left=insert(data, p.left);
      }else if(compareResult>0){
          p.right=insert(data,p.right);
      }else{
      }
      return p;
    }
    @Override
    public void remove(T data) {
     if(data==null) throw new RuntimeException("data can't be null");
     root=remove(data,root);
    }
    private BinaryNode<T> remove(T data,BinaryNode<T> p){
        if(p==null) return p;
     int compareResult=data.compareTo(p.data);
     if(compareResult<0){
         p.left=remove(data,p.left);
     }else if(compareResult>0){
         p.right=remove(data,p.right);
     }else{
         if(p.left!=null&&p.right!=null){
             p.data=findMin(p.right).data;
             p.right=remove(p.data,p.right);
         }else{
             p=(p.left!=null)?p.left:p.right;
         }
     }
     return p;
    }

    @Override
    public T findMin() {
     return findMin(root).data;
    }
    @Override
    public T findMax() {
        return findMax(root).data;
    }
    private  BinaryNode<T> findMin(BinaryNode<T> p){
        if(p==null)
            return null;
        else if(p.left==null)
            return p;
        return  findMin(p.left);
    }
    private BinaryNode<T> findMax(BinaryNode<T> p){
        if (p==null)//结束条件
            return null;
        else if (p.right==null)
            return p;
        return findMax(p.right);
    }
    @Override
    public BinaryNode findNode(T data) {
        return null;
    }

    @Override
    public boolean contains(T data) throws Exception {
        return false;
    }

    @Override
    public void clear() {
    }
    public BinaryNode<T>
    createBinarySearchTreeByPreIn(T[] preList,T[]
            inList,int preStart, int preEnd,int inStart, int inEnd){
        BinaryNode<T> p=new BinaryNode<T>(preList[preStart]);
        if(preStart==preEnd&&inStart==inEnd){
            return p;
        }
        int root=0;
        for(root=inStart;root<inEnd;root++){
            if(preList[preStart].compareTo(inList[root])==0){
                break;
            }
        }
        int leftLength=root-inStart;
        int rightLength=inEnd-root;
        if(leftLength>0){
            p.left=createBinarySearchTreeByPreIn(preList,inList,preStart+1,preStart+leftLength,inStart,root-1);
        }
        if(rightLength>0){
            p.right=createBinarySearchTreeByPreIn(preList,inList,preStart+leftLength+1,preEnd,root+1,inEnd);
        }
        return p;
    }
    public BinaryNode<T> createBinarySearchTreeByPostIn(T[] postList,T[] inList,int postStart,int postEnd,int inStart,int inEnd){
        BinaryNode<T> p=new BinaryNode<T>(postList[postEnd]);
        if(postEnd==postStart&&inStart==inEnd){
            return p;
        }
        int root=0;
        for(root=inStart;root<inEnd;root++){
            if(postList[postEnd].compareTo(inList[root])==0){
                break;
            }
        }
        int leftLength=root-inStart;
        int rightLength=inEnd-root;
        if(leftLength>0){
            p.left=createBinarySearchTreeByPostIn(postList,inList,postStart,postStart+leftLength-1,inStart,root-1);
        }
        if (rightLength>0){
            p.right=createBinarySearchTreeByPostIn(postList,inList,postStart+leftLength,postEnd-1,root+1,inEnd);
        }
     return p;
    }
}
