package MathHelper;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PointGenerator {
    private MathPoint[] ogPoints = new MathPoint[6];
    private MathPoint[] rotated = new MathPoint[6];
    private int xPos,yPos;
    private Double large;
    private int rotationOffset;
    public PointGenerator(int x, int y, int sz){
        Double d = sz+0.0;
        
    }
    public PointGenerator(int x, int y, Double size){//default point generator 0/180 degree
        //calculation(x, y, size);
        int s = (int)(Math.round(size));
        unitCirclePoints(x, y, s);
        rotationOffset = 0;
        unitCircleRotation(x, y, s);
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
    public void drawHexagon(Graphics g){
        if (rotationOffset==0){
            for (int i =0; i<5;i++){
                drawLine(g, ogPoints[i], ogPoints[i+1]);
            }
            drawLine(g,ogPoints[5],ogPoints[0]);
        }else{
            for (int i =0; i<5;i++){
                drawLine(g, rotated[i], rotated[i+1]);
            }
            drawLine(g,rotated[5],rotated[0]);
        }
        
    }
    public void setX(int i){
        xPos = i;
        calculation(xPos, yPos, large);
        RotationGenerator(xPos, yPos, large);
    }
    private void calculation(int x, int y, Double size){
        Double b = ((4.0 - Math.sqrt(7)) / 6) * size;
        Integer sz = (int) Math.round(size);
        Integer a = (int) Math.round(b);
        Integer[] xPoints = {x+(sz/2), x + sz, x + sz, x+(sz/2), x, x};
        Integer[] yPoints = {y, y + a, y + sz - a, y + sz, y + sz - a, y + a};
        for (int i =0; i<6;i++){
            ogPoints[i]= new MathPoint(xPoints[i], yPoints[i]);
        }
    }
    public void rotateCC(){
        if(rotationOffset==0){
            rotationOffset++;
        }else{
            rotationOffset--;
        }
    }
    public void rotateC(){
        if(rotationOffset==0){
            rotationOffset++;
        }else{
            rotationOffset--;
        }
    }
    public int getOffset(){
        return rotationOffset;
    }
    private void drawLine(Graphics g, MathPoint p, MathPoint a){
        g.drawLine(p.xPoint,p.yPoint,a.xPoint,a.yPoint);
    }
    public boolean isPointInsideHexagon(MouseEvent e){
        if (rotationOffset==0){
            return isPointInsideHexagon(e.getX(), e.getY());
        }else{
            return isPointInsideRotated(e.getX(), e.getY());
        }
    }
    private boolean isPointInsideRotated(int x, int y){
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        for (int i = 0; i < 6; i++) {
            xPoints[i] = rotated[i].xPoint;
            yPoints[i] = rotated[i].yPoint;
        }
        int i, j = 5;
        boolean oddNodes = false;
        for (i = 0; i < 6; i++) {
            if ((yPoints[i] < y && yPoints[j] >= y || yPoints[j] < y && yPoints[i] >= y) && (xPoints[i] <= x || xPoints[j] <= x)) {
                oddNodes ^= (xPoints[i] + (y - yPoints[i]) / (yPoints[j] - yPoints[i]) * (xPoints[j] - xPoints[i]) < x);
            }
            j = i;
        }
        return oddNodes;
    }
    private boolean isPointInsideHexagon(int x, int y) {
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        for (int i = 0; i < 6; i++) {
            xPoints[i] = ogPoints[i].xPoint;
            yPoints[i] = ogPoints[i].yPoint;
        }
        int i, j = 5;
        boolean oddNodes = false;
        for (i = 0; i < 6; i++) {
            if ((yPoints[i] < y && yPoints[j] >= y || yPoints[j] < y && yPoints[i] >= y) && (xPoints[i] <= x || xPoints[j] <= x)) {
                oddNodes ^= (xPoints[i] + (y - yPoints[i]) / (yPoints[j] - yPoints[i]) * (xPoints[j] - xPoints[i]) < x);
            }
            j = i;
        }
        return oddNodes;
    }
    private void unitCirclePoints(int x, int y, int radius){
        Double rootOffset = (Math.sqrt(3)/2)*radius;
        Double halfOffset = radius/2.0;
        int rOffset = (int)Math.round(rootOffset);
        int hOffset = (int)Math.round(halfOffset);
        Integer xPoints[] = {x,rOffset+x,rOffset+x,x,x-rOffset,x-rOffset};
        Integer yPoints[] = {y-radius,y-hOffset,y+hOffset,y+radius,y+hOffset,y-hOffset};
        for (int i =0; i<6;i++){
            ogPoints[i]= new MathPoint(xPoints[i], yPoints[i]);
        }
    }
    private void unitCircleRotation(int x, int y, int radius){
        Double rootOffset = (Math.sqrt(3)/2)*radius;
        Double halfOffset = radius/2.0;
        int rOffset = (int)Math.round(rootOffset);
        int hOffset = (int)Math.round(halfOffset);
        Integer xPoints[] = {x-hOffset,x+hOffset,x+radius,x+hOffset,x-hOffset,x-radius};
        Integer yPoints[] = {y-rOffset,y-rOffset,y,y+rOffset,y+rOffset,y};
        for(int i =0;i<6;i++){
            rotated[i]= new MathPoint(xPoints[i], yPoints[i]);
        }
    }
}
