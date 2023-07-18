package demo1;



public class Point {

    int x;
    int y;

    Point(){}

    Point(int x, int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString(){
        return "x="+x+", y="+y;
    }

    public static double getDistance(Point p1, Point p2){

        double t1=Math.pow(p1.y, 2)-Math.pow(p2.y,2);
        double t2=Math.pow(p1.x, 2)-Math.pow(p1.x,2);
        return Math.sqrt(t1+t2);
    }
    
    public double getDistance(Point p){
        
        return Math.sqrt(Math.pow(p.x, 2) - Math.pow(p.y, 2));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Point){
            if(((Point) obj).x == ((Point)obj).y){
                return true;
            }
        }
        return false;
    }
}
