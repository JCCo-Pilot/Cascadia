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
public class miniMap extends JComponent {
    private final int xSize = 295;
    private int ySize = 270;
    private Player player;
    private int xPos,yPos;
    public miniMap(int x, int y){
        super();
        xPos = x;
        yPos = y;
        this.setVisible(true);
        enableInputMethods(true);
    }
    public void paint(Graphics g){
        if (player!=null){
            g.fillRect(0,0,xSize,ySize);
            //player.drawInventory(g, false);
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
}
