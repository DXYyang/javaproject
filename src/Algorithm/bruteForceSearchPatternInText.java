package Algorithm;

/**
 * Created by admin on 2017/8/18.
 */
public class bruteForceSearchPatternInText {
    public static int bruteForceSearch(String text,String pattern){
        int slen=text.length();
        int plen=pattern.length();
        char []s=text.toCharArray();
        char []p=pattern.toCharArray();
        if (slen<plen)return -1;
        int i=0,j=0;
        while (i<slen&&j<plen){
            if(s[i]==p[j]){
                i++;
                j++;
            }else{
                i=i-j+1;
                j=0;
            }
        }
        if(j==plen)return i-j;
        else return -1;
    }

    public static void main(String[] args) {
        System.out.println(bruteForceSearch("ABQCDEFG","DEFG"));
    }
}
