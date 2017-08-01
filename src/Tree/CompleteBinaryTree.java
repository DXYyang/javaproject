package Tree;

import Queue.LinkedQueue;


/**
 * Created by admin on 2017/7/31.
 */
public class CompleteBinaryTree<T extends Comparable>extends BinarySearchTree<T> {
    public CompleteBinaryTree(){
        super();
    }
    public CompleteBinaryTree(T[] levelOrderArray){
        this.root=create(levelOrderArray,0);
    }
    public  CompleteBinaryTree(BinaryNode<T> tree){
        this.root=tree;
    }
    public BinaryNode<T> create(T[] levelOrderArray,int i) {
        if(levelOrderArray==null){
            throw new RuntimeException("the param array can't be null!");
        }
        BinaryNode<T> p=null;
        if(i<levelOrderArray.length){
            p=new BinaryNode<T>(levelOrderArray[i],null,null);
            p.left=create(levelOrderArray,2*i+1);
            p.right=create(levelOrderArray,2*i+2);
        }
        return p;
    }

    @Override
    public boolean contains(T data) throws Exception {
        LinkedQueue<BinaryNode<T>> queue=new LinkedQueue<BinaryNode<T>>();
        StringBuffer sb=new StringBuffer();
        BinaryNode<T> p=this.root;
        while (p!=null){
            if(data.compareTo(p.data)==0){

                return true;
            }
            if(p.left!=null) queue.add(p.left);
            if(p.right!=null) queue.add(p.right);
            p=queue.poll();
        }
        return false;
    }

    @Override
    public void remove(T data) {
    }
    @Override
    public void insert(T data) {
    }
    public static void main(String[] args) {
     String[] levelorderArray={"A","B","C","D","E","F"};
     String[] preorderArray={"A","B","D","G","C","E","F","H"};
     String[] inorderArray={"D","G","B","A","E","C","H","F"};
     String[] postorderArray={"G","D","B","E","H","F","C","A"};
     CompleteBinaryTree<String> cbtree=new CompleteBinaryTree<String>(levelorderArray);
        System.out.println("先根遍历:"+cbtree.preOrder());
        System.out.println("非递归先根遍历:"+cbtree.preOrderTraverse());
        System.out.println("中根遍历:"+cbtree.inOrder());
        System.out.println("非递归中根遍历:"+cbtree.inOrderTraverse());
        System.out.println("后根遍历:"+cbtree.postOrder());
        System.out.println("非递归后根遍历:"+cbtree.postOrderTraverse());
        System.out.println("查找最大结点(根据搜索二叉树):"+cbtree.findMax());
        System.out.println("查找最小结点(根据搜索二叉树):"+cbtree.findMin());
        try {
            System.out.println("判断二叉树中是否存在E:"+cbtree.contains("E"));
        } catch (Exception e) {
            e.printStackTrace();
        }
     BinaryNode<String> p=cbtree.createBinarySearchTreeByPreIn(preorderArray,inorderArray,0,preorderArray.length-1,0,inorderArray.length-1);
      CompleteBinaryTree<String> createtree=new CompleteBinaryTree<String>(p);
      System.out.println("preorder:"+createtree.preOrder());
      System.out.println("inorder:"+createtree.inOrder());
      System.out.println("postorder:"+createtree.postOrder());
      BinaryNode<String> p1=cbtree.createBinarySearchTreeByPostIn(postorderArray,inorderArray,0,postorderArray.length-1,0,inorderArray.length-1);
        CompleteBinaryTree<String> createtree2=new CompleteBinaryTree<String>(p);
        System.out.println("preorder:"+createtree2.preOrder());
        System.out.println("inorder:"+createtree2.inOrder());
        System.out.println("postorder:"+createtree2.postOrder());
          }


}
