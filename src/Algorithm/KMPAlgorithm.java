package Algorithm;

/**
 * Created by admin on 2017/8/18.
 */
public class KMPAlgorithm {
    private static int [] next(String mode){
        String newMode="x"+mode;
        int[] K=new int[newMode.length()];
        int i=1;
        K[1]=0;
        int j=0;
        while(i<mode.length()){
            if(j==0||newMode.charAt(i)==newMode.charAt(j)){
                i++;
                j++;
                K[i]=j;
            }else{
                j=K[j];
            }
        }
        return K;
    }
    public static int matchString(String target,String mode){
        String newTarget="x"+target;
        String newMode="x"+mode;
        int[] next=next(mode);
        int i=1,j=1;
        while(i<=target.length()&&j<=mode.length()){
            if(j==0||newTarget.charAt(i)==newMode.charAt(j)){
                i++;
                j++;
            }else{
                j=next[j];
            }
        }
        if(j>mode.length()){
            return i-j;
        }
        return -1;
    }

    public static void main(String[] args) {
        String a = "bbbabc";
        String b = "abc";
        System.out.println(KMPAlgorithm.matchString(a,b));
    }
}
