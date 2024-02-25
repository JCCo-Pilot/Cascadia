package Components;
import Entities.*;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import MathHelper.*;
import static java.lang.System.*;
public class PlayerDisplay extends JComponent implements MouseListener{
    private int xSize,ySize;
    private int xPos,yPos;
    private ArrayList<PointGenerator>testHexagons = new ArrayList<>();
    /*private PointGenerator pg1 = new PointGenerator(0, 0, 70.0);
    private PointGenerator pg2 = new PointGenerator(100, 0, 70.0);
    private PointGenerator pg3 = new PointGenerator(50, 77, 70.0);
    private PointGenerator pg4 = new PointGenerator(150, 77, 70.0);*/
    public PlayerDisplay(int x, int y, int xS, int yS){
        super();
        this.setVisible(true);
        xPos = x; yPos = y;
        xSize = xS; ySize = yS;
        constructHexagons();
        enableInputMethods(true);
        addMouseListener(this);
    }
    private void constructHexagons(){//replace all the 50.0 with 100.0
        //y increments are radius*1.5
        //x increments are size* root(3)/2
        int size = 70;
        Double inc = size*Math.sqrt(3)/2;
        int xInc = (int)(Math.round(inc));
        out.println(xInc);
        for (int i =0; i<7;i++){
            testHexagons.add(new PointGenerator(xInc*2*(i+1)-30, 0+105, 70.0));
            testHexagons.add(new PointGenerator(xInc*2*(i+1)-30, 105*2+105, 70.0));
            testHexagons.add(new PointGenerator(xInc*2*(i+1)-30, 105*4+105, 70.0));
            testHexagons.add(new PointGenerator(xInc*2*(i+1)-30, 105*6+105, 70.0));
        }
        for(int i =0; i<6;i++){
            testHexagons.add(new PointGenerator(xInc+(122*i)-30+122, 105+105, 70.0));
            testHexagons.add(new PointGenerator(xInc+(122*i)-30+122, 105*3+105, 70.0));
            testHexagons.add(new PointGenerator(xInc+(122*i)-30+122, 105*5+105, 70.0));
            //testHexagons.add(new PointGenerator(xInc+(122*i)-30+122, 105*7+105, 70.0));
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D antiAlias = (Graphics2D) g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paint(g);
    }
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        Polygon p = new Polygon();
        for (int i =0; i<testHexagons.size();i++){
            testHexagons.get(i).drawHexagon(g);
        }
        /*pg1.drawHexagon(g);
        pg2.drawHexagon(g);
        pg3.drawHexagon(g);
        pg4.drawHexagon(g);*/
    }
    public Dimension getPreferredSize() {return new Dimension(xSize, ySize);}
    public Dimension getMinimumSize() {return new Dimension(xSize, ySize );}
    public Dimension getMaximumSize() {return new Dimension(xSize , ySize );}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        for(int i =0;i<testHexagons.size();i++){
            if(testHexagons.get(i).isPointInsideHexagon(e)){
                testHexagons.get(i).clicked();
                
            }
        }
        repaint();
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public int getXPos(){return xPos;}
    public int getYPos(){return yPos;}
    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}
}
