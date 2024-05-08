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
public class animalDisplay extends JComponent implements MouseListener{
    private final int xSize = 70;
    private final int ySize = 70;
    private WildlifeTokens token;
    private int xPos;
    private int yPos;
    private int numClicks;
    private boolean allowClick;
    public animalDisplay(int x, int y){
        super();
        xPos = x;
        yPos = y;
    }
    @Override
    public void paintComponent(Graphics g){

    }
    @Override
    public void paint(Graphics g){
        if (token!=null){
            g.drawImage(token.getImage(),0,0,70,70,null);
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e){
        if (allowClick){
            numClicks++;
        }
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public Dimension getPreferredSize() {return new Dimension(xSize, ySize);}
    public Dimension getMinimumSize() {return new Dimension(xSize, ySize );}
    public Dimension getMaximumSize() {return new Dimension(xSize , ySize );}
}
