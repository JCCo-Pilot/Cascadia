package Components;
import Entities.*;
import Entities.Enums.CardAnimals;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.print.DocFlavor.INPUT_STREAM;
import java.awt.image.*;
import java.io.File;
import MathHelper.*;
import Panels.ScoreTesterPanel;
import Panels.*;
import static java.lang.System.*;
public class miniMap extends JComponent implements MouseListener{
    private final int xSize = 295;
    private int ySize = 270;
    private Player player;
    private int xPos,yPos;
    private UpdateEventListener uListener;
    public miniMap(int x, int y){
        super();
        xPos = x;
        yPos = y;
        this.setVisible(true);
        this.addMouseListener(this);
        enableInputMethods(true);
    }
    public void paint(Graphics g){
        if (player!=null){
            g.drawString(player.getName()+",",0,10);
            g.drawString("Turns Left: "+player.getTurn(),50,10);
            //g.fillRect(0,0,xSize,ySize);
            player.getGraph().drawGraph(g, 0, 0, xSize, ySize-10);;
        }
    }
    @Override
    public void paintComponent(Graphics g){
        paint(g);
    }
    public Dimension getPreferredSize() {return new Dimension(xSize, ySize);}
    public Dimension getMinimumSize() {return new Dimension(xSize, ySize );}
    public Dimension getMaximumSize() {return new Dimension(xSize , ySize );}
    public int getXPos(){return xPos;}
    public int getYPos(){return yPos;}
    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}
    public void setYSize(int ys){ySize = ys;}
    public void setPlayer(Player p){
        player = p;
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        out.println("Clicked");
        UpdateEvent ue = new UpdateEvent(this,e);
        uListener.update(ue);
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void setUListener(UpdateEventListener ul){
        uListener = ul;
    }
}
