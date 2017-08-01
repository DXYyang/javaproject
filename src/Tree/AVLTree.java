package Tree;

import Queue.LinkedQueue;
import Stack.LinkedStack;

/**
 * Created by admin on 2017/8/1.
 */
public class AVLTree<T extends  Comparable>implements Tree<T> {
    protected AVLNode<T> root;
    public AVLTree(){
        root=null;
    }
    private AVLNode<T> singleRotateLeft(AVLNode<T> x){
      AVLNode<T> w=x.left;
      x.left=w.right;
      w.right=x;
      x.height=Math.max(height(x.left),height(x.right))+1;
      w.height=Math.max(height(w.left),x.height)+1;
      return w;
     }
     private AVLNode<T> singleRotateRight(AVLNode<T> x){
        AVLNode<T> w=x.right;
        x.right=w.left;
        w.left=x;
        x.height=Math.max(height(x.left),height(x.right))+1;
        w.height=Math.max(x.height,height(w.right))+1;
        return w;
     }
     private AVLNode<T> doubleRotateWithLeft(AVLNode<T> x){
         x.left=singleRotateRight(x.left);
         return singleRotateLeft(x);
     }
     private AVLNode<T> doubleRotateWithRight(AVLNode<T> x){
         x.right=singleRotateLeft(x.right);
         return singleRotateRight(x);
     }
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    public int height() {
        return height(root);
    }

    private int height(AVLNode<T> subtree) {
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
        LinkedStack<AVLNode<T>> stack=new LinkedStack<AVLNode<T>>();
        AVLNode<T> p=this.root;
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
    private String preOrder(AVLNode<T> subtree){
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
    private String inOrder(AVLNode<T> subtree) {
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
        LinkedStack<AVLNode<T>> stack=new LinkedStack<AVLNode<T>>();
        AVLNode<T> p=this.root;
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
    private String postOrder(AVLNode<T> subtree) {
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
        LinkedStack<AVLNode<T>> stack=new LinkedStack<AVLNode<T>>();
        AVLNode<T> currentNode=this.root;
        AVLNode<T> prev=this.root;
        while(currentNode!=null||!stack.isEmpty()){
            while(currentNode!=null){
                stack.push(currentNode);
                currentNode=currentNode.left;
            }
            if(!stack.isEmpty()){
                AVLNode<T> temp=stack.peek().right;
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
        LinkedQueue<AVLNode<T>> queue=new LinkedQueue<AVLNode<T>>();
        StringBuffer sb=new StringBuffer();
        AVLNode<T> p=this.root;
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
    public void insertByArray(T[] array){
        for(T item:array){
          insert(item);
        }
    }
    @Override
    public void insert(T data) {
   this.root=insert(data,root);
    }
  private AVLNode<T> insert(T data,AVLNode<T> p){
         if(p==null){
             p=new AVLNode<T>(data);
         }else if(data.compareTo(p.data)<0){
             p.left=insert(data,p.left);
             if(height(p.left)-height(p.right)==2){
                 if(data.compareTo(p.left.data)<0){
                     p=singleRotateLeft(p);
                 }else{
                     p=doubleRotateWithLeft(p);
                 }
             }
         }else if(data.compareTo(p.data)>0){
             p.right=insert(data,p.right);
             if(height(p.right)-height(p.left)==2){
                 if(data.compareTo(p.right.data)<0){
                     p=doubleRotateWithRight(p);
                 }else{
                     p=singleRotateRight(p);
                 }
             }
         } else{
         }
         p.height=Math.max(height(p.left),height(p.right))+1;
         return p;
  }
    @Override
    public void remove(T data) {
     this.root=remove(data,root);
    }
    private AVLNode<T> remove(T data,AVLNode<T> p){
      if(p==null){
          return null;
      }
      int result=data.compareTo(p.data);
      if(result<0){
          p.left=remove(data,p.left);
          if(height(p.right)-height(p.left)==2){
              AVLNode<T> currentNode=p.right;
          if(height(currentNode.left)>height(currentNode.right)){
              p=doubleRotateWithRight(p);
          }else{
              p=singleRotateRight(p);
          }
          }
      }
      else if(result>0){
          p.right=remove(data,p.right);
          if(height(p.left)-height(p.right)==2){
              AVLNode<T> currentNode=p.left;
              if(height(currentNode.right)>height(currentNode.left)){
                  p=doubleRotateWithLeft(p);
              }else{
                  p=singleRotateLeft(p);
              }
          }
      }
      else{
          if(p.right!=null&&p.left!=null){
              p.data=findMin(p.right).data;
              p.right=remove(p.data,p.right);
          }else{
              p=(p.left!=null)?p.left:p.right;
          }
      }
      if(p!=null) p.height=Math.max(height(p.left),height(p.right))+1;
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
    private  AVLNode<T> findMin(AVLNode<T> p){
        if(p==null)
            return null;
        else if(p.left==null)
            return p;
        return  findMin(p.left);
    }
    private AVLNode<T> findMax(AVLNode<T> p){
        if (p==null)//结束条件
            return null;
        else if (p.right==null)
            return p;
        return findMax(p.right);
    }

    @Override
    public BinaryNode<T> findNode(T data) {
        return null;
    }

    @Override
    public boolean contains(T data) throws Exception {
        return false;
    }

    @Override
    public void clear() {

    }

    public static void main(String[] args) {
        AVLTree<Integer> AVLtree=new AVLTree<Integer>();
         Integer[] test={5,2,8,1,4,7,9,3};
         AVLtree.insertByArray(test);
         System.out.println(AVLtree.preOrder());
         System.out.println(AVLtree.inOrder());
         System.out.println(AVLtree.postOrder());
         System.out.println(AVLtree.levelOrder());
         AVLtree.remove(2 );
        System.out.println(AVLtree.preOrder());
        System.out.println(AVLtree.inOrder());
        System.out.println(AVLtree.postOrder());
        System.out.println(AVLtree.levelOrder());
    }
}