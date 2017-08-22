package Algorithm;

/**
 * Created by admin on 2017/8/21.
 */
public class BestSchedule {
    int n=3;
    int [][] mission={{2,1},{3,1},{2,3}};
    int bestFinishtime=Integer.MAX_VALUE;
    int[] schedule={0,1,2};
    int[] bestSchedule=new int[n];
    int[] f2=new int[n];
    int f1,totalTime;
    public void swap(int[] array,int m,int n){
        int temp=array[m];
        array[m]=array[n];
        array[n]=temp;
    }
    public void BackTrack(int t){
        if(t>n-1){
            bestFinishtime=totalTime;
            for(int i=0;i<n;i++)
                bestSchedule[i]=schedule[i];
            return;
        }
        for(int i=t;i<n;i++){
            f1+=mission[schedule[i]][0];
            if(t==0)f2[t]=f1+mission[schedule[i]][1];
            else f2[t]=((f2[t-1]>f1)?f2[t-1]:f1)+mission[schedule[i]][1];
            totalTime+=f2[t];
            if(totalTime<bestFinishtime){
                swap(schedule,t,i);
                BackTrack(t+1);
                swap(schedule,t,i);
            }
            f1-=mission[schedule[i]][0];
            totalTime-=f2[t];
        }
    }

    public static void main(String[] args) {
        BestSchedule bs=new BestSchedule();
        bs.BackTrack(0);
        System.out.println("最佳调度方案为：");
        for(int i=0;i<bs.n;i++)
            System.out.println(bs.bestSchedule[i]+" ");
        System.out.println();
        System.out.println("其完成时间为："+bs.bestFinishtime);
    }
}
