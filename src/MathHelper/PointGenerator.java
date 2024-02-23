package MathHelper;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PointGenerator {
    private MathPoint[] ogPoints = new MathPoint[6];
    private int xPos,yPos;
    private Double radius;
    private boolean clicked;
    public PointGenerator(int x, int y, int sz){
        radius = sz+0.0;
        unitCirclePoints(x, y, sz);
        xPos = x; yPos = y;
    }
    public PointGenerator(int x, int y, Double sz){//default point generator 0/180 degree
        //calculation(x, y, size);
        int s = (int)(Math.round(sz));
        unitCirclePoints(x, y, s);
        xPos = x; yPos = y;
        radius = sz;
    }
    public void drawHexagon(Graphics g){
        if (clicked){
            myPolygon mp = new myPolygon(ogPoints);
            g.fillPolygon(mp);
        }
        for (int i =0; i<5;i++){
            drawLine(g, ogPoints[i], ogPoints[i+1]);
        }
        drawLine(g,ogPoints[5],ogPoints[0]);
    }
    public void setX(int i){
        xPos = i;
        unitCirclePoints(xPos, yPos, (int)Math.round(radius));
    }
    public Double getSize(){
        return radius;
    }
    public void clicked(){
        clicked= !clicked;
    }
    private void drawLine(Graphics g, MathPoint p, MathPoint a){
        g.drawLine(p.xPoint,p.yPoint,a.xPoint,a.yPoint);
    }
    public boolean isPointInsideHexagon(MouseEvent e){
        return isPointInsideHexagon(e.getX(), e.getY());
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
}
