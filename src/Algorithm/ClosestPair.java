package Algorithm;

import java.util.*;

/**
 * Created by admin on 2017/8/17.
 */
public class ClosestPair {
    static class Point{
        double x;
        double y;
        public Point(){

        }
        public Point(double x,double y){
            this.x=x;
            this.y=y;
        }
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(true){
            int n=scanner.nextInt();
            Point[] ps=new Point[n];
            for(int i=0;i<n;i++) {
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                ps[i] = new Point(x, y);
            }
                Arrays.sort(ps, new Comparator<Point>() {
                    @Override
                    public int compare(Point o1, Point o2) {
                        if(o1.x<o2.x)return -1;
                        if(o1.x>o2.x)return 1;
                        if(o1.y<o2.y)return -1;
                        if(o1.y>o2.y)return 1;
                        return 0;
                    }
                });

           double minDis=minDistance(ps,0,n-1);
           System.out.println(minDis);
        }
    }
    public static double distance(Point p1,Point p2){
        if(p1==p2)return 0;
        return Math.sqrt(Math.pow(p1.x-p2.x,2)+Math.pow(p1.y-p2.y,2));
    }
    public static double minDistance(Point[] ps,int l,int r){
        if(l==r) return Double.MAX_VALUE;
        if(l+1==r)return distance(ps[l],ps[r]);
        int center=l+(r-l)/2;
        double dist1=minDistance(ps,l,center);
        double dist2=minDistance(ps,center+1,r);
        double minDis=Math.min(dist1,dist2);
        ArrayList<Point>nearPoints=new ArrayList<>();
        for(Point p:ps){
            if(Math.abs(ps[center].x-p.x)<=minDis){
                nearPoints.add(p);
            }
        }
        Collections.sort(nearPoints, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.y<o2.y)return -1;
                if(o1.y>o2.y)return 1;
                if(o1.x<o2.x)return -1;
                if(o1.x>o2.x)return 1;
                return 0;
            }
        });
        for(int i=0;i<nearPoints.size();i++){
            for(int j=i+1;j<nearPoints.size();j++){
                if(nearPoints.get(j).y-nearPoints.get(i).y>minDis)break;
                double d=distance(nearPoints.get(j),nearPoints.get(i));
                if(d<minDis)minDis=d;
            }
        }
        return minDis;
    }
}
