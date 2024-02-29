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
    public scoringDisplay(HashMap<Integer,Player>play , int num, int x, int y, int xs, int ys){
        numPlayers = num;
        players = play;
    }
    public void paint(Graphics g){
        g.setColor(Color.CYAN);
        
    }
    public void actionPerformed(ActionEvent e){

    }
}
