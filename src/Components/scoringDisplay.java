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
        construct();
    }
    public void construct(){
        //coordinates for buttons x - 1020 y - 690, 740, 790, 840 w - 560 h- 50
        for (int i =0;i<numPlayers;i++){
            JButton temp = new JButton("Player "+(i+1));
            temp.setBounds(1020,690+(i*50),560,50);
            temp.setVisible(true);
            playerButtons.add(temp);
        }
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
