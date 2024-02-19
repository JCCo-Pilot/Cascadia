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
    private int xPos,yPos;
    public PickArea(int i,int x, int y , int xS, int yS){
        super();
        construct(x,y,xS,yS);
        numPlayers = i;
        this.setVisible(true);
        createTokens();
        randShuffle();
        createHabitatTiles();
    }
    private void construct(int x, int y, int xS, int yS){
        enableInputMethods(true);
        addMouseListener(this);
        xPos = x; yPos = y;
        xSize = xS; ySize = yS;
    }
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, xSize, ySize);
        g.setColor(Color.RED);
        //spacing+(size+space)*i
        for (int i = 0;i<4;i++){
            g.fillRect(6+(106)*i, 6, 100, 100);
        }
        g.setColor(Color.BLUE);
        for (int i = 0;i<4;i++){
            g.fillOval(31+(106)*i, 111, 50, 50);
        }
        g.fillRect(324+106,111,94,50);
        g.fillRect(324+106,6,94,100);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D antiAlias = (Graphics2D) g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paint(g);
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
    public int getXPos(){return xPos;}
    public int getYPos(){return yPos;}
    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}
}
