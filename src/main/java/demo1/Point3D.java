package demo1;

public class Point3D extends Point{

    int z;
    Point3D(){
        super.x=1;
        super.y=1;
        this.z=1;
    }
    Point3D(int x, int y, int z){
        super.x=x;
        super.y=y;
        this.z=z;
    }
    @Override
    public String toString(){

        return "x="+super.x+", y="+super.y+", z="+this.z;
    }


}
