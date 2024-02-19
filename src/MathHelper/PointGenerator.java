package MathHelper;
import java.util.*;
public class PointGenerator {
    private MathPoint[] ogPoints = new MathPoint[6];
    private MathPoint[] rotated = new MathPoint[6];
    private int rotationOffset;
    public PointGenerator(int x, int y, Double size){//default point generator 0/180 degree
        Double b = ((4.0 - Math.sqrt(7)) / 6) * size;
        Integer sz = (int) Math.round(size);
        Integer a = (int) Math.round(b);
        Integer[] xPoints = {x+(sz/2), x + sz, x + sz, x+(sz/2), x, x};
        Integer[] yPoints = {y, y + a, y + sz - a, y + sz, y + sz - a, y + a};
        for (int i =0; i<6;i++){
            ogPoints[i]= new MathPoint(xPoints[i], yPoints[i]);
        }
        rotationOffset = 0;
        RotationGenerator(x, y, size);
    }
    private void RotationGenerator(int x, int y, Double size){
        Double b = ((4.0 - Math.sqrt(7)) / 6) * size;
        Integer s = (int) Math.round(size);
        Integer a = (int) Math.round(b);
        Integer[] xPoints ={x+a,x+s-a,x+s,x+s-a,x+a,x};
        Integer[] yPoints= {y,y,y+(s/2),y+s,y+s,y+(s/2)};
        for(int i =0;i<6;i++){
            rotated[i]= new MathPoint(xPoints[i], yPoints[i]);
        }
    }
    public void rotateCC(){

    }
    public void rotateC(){
        
    }
    public int getOffset(){
        return rotationOffset;
    }
}
