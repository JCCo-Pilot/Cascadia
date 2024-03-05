package Components;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Entities.*;
import EventAndListener.*;
import MathHelper.*;

public class scoringDisplay extends JComponent implements ActionListener{
    private HashMap<Integer,Player>players;
    private int numPlayers;
    private int xPos,yPos;
    private int xSize,ySize;
    private ArrayList<JButton>playerButtons = new ArrayList<>();
    public scoringDisplay(HashMap<Integer,Player>play , int num, int x, int y, int xs, int ys){
        numPlayers = num;
        players = play;
        xPos = x; yPos = y;
        xSize = xs; ySize = ys;
    }
    public void paintComponents(Graphics g){
        g.drawRect(0,0,100,100);
    }
    public int getXPos(){return xPos;}
    public int getYPos(){return yPos;}
    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}
    public Dimension getPreferredSize(){return new Dimension(xSize,ySize);}
    public Dimension getMinimuSize(){return getPreferredSize();}
    public Dimension getMaximumSize(){return getPreferredSize();}
    public void paint(Graphics g){
        g.setColor(Color.CYAN);
        
    }
    public void actionPerformed(ActionEvent e){

    }
}
