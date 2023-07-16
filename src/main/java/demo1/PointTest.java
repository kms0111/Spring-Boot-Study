package demo1;

public class PointTest {

    public static void main(String[] args) {
        Point point = new Point();
        point.x=3;
        point.y=5;
        System.out.println("myPoint.toString() = " + point.toString());

        Point point1 = new Point(1,1);
        System.out.println("myPoint1.toString() = " + point1.toString());

        Point3D point3D = new Point3D(1,2,3);
        System.out.println("point3D.toString() = " + point3D.toString());
        
        Point3D point3D1 = new Point3D();
        System.out.println("point3D1 = " + point3D1);

    }

}
