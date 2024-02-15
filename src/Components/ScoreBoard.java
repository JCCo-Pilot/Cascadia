package Components;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import static java.lang.System.*;
public class ScoreBoard extends JComponent implements MouseListener{
    private int xSize,ySize;
    private int xPos,yPos;
    public ScoreBoard(){
        
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D antiAlias = (Graphics2D) g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void setX(int x){xPos = x;}
    public void setY(int x){yPos = x;}
    public int getX(){return xPos;}
    public int getY(){return yPos;}
    public Dimension getPreferredSize(){return new Dimension(xSize,ySize);}
    public Dimension getMinimumSize(){return getPreferredSize();}
    public Dimension getMaximumSize(){return getPreferredSize();}
}
