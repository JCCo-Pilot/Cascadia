package Components;
import Entities.*;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import static java.lang.System.*;
public class PickArea extends JComponent implements MouseListener{
    private ArrayList<WildlifeTokens>tokens = new ArrayList<>();
    private int numPlayers;
    private int xSize,ySize;
    public PickArea(int i){
        numPlayers = i;
        xSize = 100;
        ySize = 800;
        createTokens();
        randShuffle();
        createHabitatTiles();
    }
    public void paint(Graphics g){

    }
    public void paintComponent(Graphics g){
        
    }
    private void createTokens(){
        for (int i =0;i<20;i++){
            tokens.add(new WildlifeTokens(0));
            tokens.add(new WildlifeTokens(1));
            tokens.add(new WildlifeTokens(2));
            tokens.add(new WildlifeTokens(3));
        }
    }
    private void createHabitatTiles(){
        //create all 85 tiles
        switch(numPlayers){
            case 2:
                //remove 42
            break;
            case 3:
                //remove 22
            break;
            case 4:
                //remove 2 
            break;
        }
    }
    private void randShuffle(){
        int numTime = (int)(Math.random()*90)+10;
        for (int i = 0;i<numTime;i++){
            Collections.shuffle(tokens);
        }
    }
    private void sumChecker(){
        int numBear = 0;
        int numFox = 0;
        int numElk = 0;
        int numSalmon = 0;
        for (int i = 0; i<tokens.size();i++){
            if (tokens.get(i).getName().equals("Bear")){
                numBear++;
            }else if (tokens.get(i).getName().equals("Elk")){
                numElk++;
            }else if (tokens.get(i).getName().equals("Salmon")){
                numSalmon++;
            }else if (tokens.get(i).getName().equals("Fox")){
                numFox++;
            }
        }
        out.println("Bears -"+numBear);
        out.println("Elks -"+numElk);
        out.println("Salmon -"+numSalmon);
        out.println("Fox -"+numFox);
    }
    public Dimension getPreferredSize() {return new Dimension(xSize, ySize);}
    public Dimension getMinimumSize() {return new Dimension(xSize, ySize );}
    public Dimension getMaximumSize() {return new Dimension(xSize , ySize );}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
