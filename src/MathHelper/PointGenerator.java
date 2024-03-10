package MathHelper;
import Entities.*;
import Entities.Enums.CardAnimals;

import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.print.DocFlavor.INPUT_STREAM;

import java.awt.image.*;
import static java.lang.System.*;
public class PointGenerator {
    private MathPoint[] ogPoints = new MathPoint[6];
    private int xPos,yPos;
    private Double radius;
    private boolean clicked;
    private myPolygon myPoly;
    private WildlifeTokens test;
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
    public int getXPos(){
        return xPos;
    }
    public int getYPos(){
        return yPos;
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
        // part of the test code will remove later
        if (test!=null){
            g.drawImage(test.getImage(),xPos-35,yPos-35,70,70,null);
        }
        //end of test code
    }
    public void setX(int i){
        xPos = i;
        unitCirclePoints(xPos, yPos, (int)Math.round(radius));
    }
    public void setY(int i){
        yPos = i;
        unitCirclePoints(xPos, yPos, (int)(Math.round(radius)));
    }
    public Double getSize(){
        return radius;
    }
    public void clicked(){
        clicked= !clicked;
    }
    //test code will remove later
    public void addToken(WildlifeTokens t){
        test = t;
    }
    public WildlifeTokens getTokens(){
        return test;
    }
    //end of removal
    private void drawLine(Graphics g, MathPoint p, MathPoint a){
        g.drawLine(p.xPoint,p.yPoint,a.xPoint,a.yPoint);
    }
    public boolean isPointInsideHexagon(MouseEvent e){
        return myPoly.contains(e.getX(), e.getY());
        //return isPointInsideHexagon(e.getX(), e.getY());
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
        myPoly = new myPolygon(ogPoints);
    }
    public void setPos(int x, int y, Double sz){
        xPos = x;
        yPos = y;
        radius = sz;
        unitCirclePoints(x, y, (int)(Math.round(sz)));
    }
}
