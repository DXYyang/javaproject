import java.util.*;

/**
 * Created by admin on 2017/7/24.
 */
public class test {
    public static void main(String[] args) {
        List dataList=VirtualDataGenerator.getVirtualResult();
        HashMap nodeList=new HashMap();
        Node root =null;
        for(Iterator it=dataList.iterator();it.hasNext();){
            Map dataRecord=(Map)it.next();
            Node node=new Node();
            node.id = (String) dataRecord.get("id");
            node.text = (String) dataRecord.get("text");
            node.parentId = (String) dataRecord.get("parentId");
            nodeList.put(node.id,node);
        }
        Set entrySet=nodeList.entrySet();
        for(Iterator it=entrySet.iterator();it.hasNext();){
            Node node=(Node)((Map.Entry)it.next()).getValue();
            if(node.parentId==null||node.parentId.equals("")){
                root=node;
            }else{
                ((Node)nodeList.get(node.parentId)).addChild(node);
            }
        }
        System.out.println(root.toString());
        root.sortChildren();
        System.out.println(root.toString());
    }
}
