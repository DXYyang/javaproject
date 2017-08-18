package Algorithm;

/**
 * Created by admin on 2017/8/18.
 */
public class stringCompare {
    public static String getLCSLength(String s,String t){
        int p=s.length();
        int q=t.length();
        String num[][]=new String[p][q];
        char char1,char2;
        int len=0;
        String lcs="";
        for(int i=0;i<p;i++){
            for(int j=0;j<q;j++){
                char1=s.charAt(i);
                char2=t.charAt(j);
                if(char1!=char2){
                    num[i][j]="";
                }
                else{
                    if(i==0||j==0) num[i][j]=String.valueOf(char1);
                    else  num[i][j]=num[i-1][j-1]+String.valueOf(char1);
                    if(num[i][j].length()>len){
                        len=num[i][j].length();
                        lcs=num[i][j];
                    }
                    else if(num[i][j].length()==len)
                        lcs=lcs+","+num[i][j];
                }
            }
        }
        return lcs;
    }

    public static void main(String[] args) {
        System.out.println(getLCSLength("abab","baba"));
    }
}
