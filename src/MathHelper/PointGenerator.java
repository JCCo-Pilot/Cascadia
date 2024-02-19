package MathHelper;
import java.util.*;
public class PointGenerator {
    private MathPoint[] ogPoints = new MathPoint[6];
    private MathPoint[] rotated = new MathPoint[6];
    public PointGenerator(int x, int y, Double size){//default point generator 0/180 degree
        Double b = ((4.0 - Math.sqrt(7)) / 6) * size;
        Integer sz = (int) Math.round(size);
        Integer a = (int) Math.round(b);
        Integer[] xPoints = {x, x + sz, x + sz, x, x, x};
        Integer[] yPoints = {y, y + a, y + sz - a, y + sz, y + sz - a, y + a};
        for (int i =0; i<6;i++){
            ogPoints[i]= new MathPoint(xPoints[i], yPoints[i]);
        }
    }
}
