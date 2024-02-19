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
    /*private PointGenerator pg1 = new PointGenerator(0, 0, 100.0);
    private PointGenerator pg2 = new PointGenerator(100, 0, 100.0);
    private PointGenerator pg3 = new PointGenerator(50, 77, 100.0);
    private PointGenerator pg4 = new PointGenerator(150, 77, 100.0);*/
    public PlayerDisplay(int x, int y, int xS, int yS){
        super();
        this.setVisible(true);
        xPos = x; yPos = y;
        xSize = xS; ySize = yS;
        constructHexagons();
        enableInputMethods(true);
        addMouseListener(this);
    }
    private void constructHexagons(){
        for (int i =0; i<8;i++){
            testHexagons.add(new PointGenerator(100*i+100, 0, 100.0));
            testHexagons.add(new PointGenerator(100*i+100, 77*2, 100.0));
            testHexagons.add(new PointGenerator(100*i+100, 77*4, 100.0));
        }
        for(int i =0; i<9;i++){
            testHexagons.add(new PointGenerator(50+(100*i), 77, 100.0));
            testHexagons.add(new PointGenerator(50+(100*i), 77*3, 100.0));
            testHexagons.add(new PointGenerator(50+(100*i), 77*5, 100.0));
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
        g.setColor(Color.GREEN);
        g.fillRect(0,0,xSize,ySize);
        g.setColor(Color.BLACK);
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
        
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public int getXPos(){return xPos;}
    public int getYPos(){return yPos;}
    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}
}
