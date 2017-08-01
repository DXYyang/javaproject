import java.util.*;

/**
 * Created by admin on 2017/7/24.
 */
public class Children {
    private List list=new ArrayList();
    public int getSize(){
        return list.size();
    }
    public void addChild(Node node){
        list.add(node);
    }
    public String toString(){
        String result="[";
        for(Iterator it=list.iterator();it.hasNext();){
            result+=((Node)it.next()).toString();
            result+=",";
        }
        result=result.substring(0,result.length()-1);
        result+="]";
        return result;
    }
    public void sortChildren(){
        Collections.sort(list,new NodeIDComparator());
        for(Iterator it=list.iterator();it.hasNext();){
            ((Node)it.next()).sortChildren();
        }
    }
}
