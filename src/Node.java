/**
 * Created by admin on 2017/7/24.
 */
public class Node
{
    public String id;
    public String text;
    public String parentId;
    public Children children=new Children();
    public String toString() {
        String result = "{"
                + "id : '" + id + "'"
                + ", text : '" + text + "'";

        if (children != null && children.getSize() != 0) {
            result += ", children : " + children.toString();
        } else {
            result += ", leaf : true";
        }

        return result + "}";
    }
    public void sortChildren(){
        if(children!=null&&children.getSize()!=0){
            children.sortChildren();
        }
    }
    public void addChild(Node node){
        this.children.addChild(node);
    }

}
