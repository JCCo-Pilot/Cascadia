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
public class PickArea extends JComponent implements MouseListener{

    private ArrayList<WildlifeTokens>tokens = new ArrayList<>();
    private int numPlayers;
    private int xSize,ySize;
    private int xPos,yPos;
    private JButton jb = new JButton("Over-Population");
    private PointGenerator[]hexagons = new PointGenerator[4];
    public PickArea(int i,int x, int y , int xS, int yS){
        super();
        construct(x,y,xS,yS);
        numPlayers = i;
        this.setVisible(true);
        createTokens();
        //sumChecker();
        randShuffle();
        createHabitatTiles();

        jb.setBounds(0,800,100,50);
        jb.setVisible(true);
        add(jb);
        
        setVisible(true);
    }
    private void construct(int x, int y, int xS, int yS){
        enableInputMethods(true);
        addMouseListener(this);
        xPos = x; yPos = y;
        xSize = xS; ySize = yS;
        for (int i = 0;i<4;i++){
            PointGenerator pg = new PointGenerator(56, 250+(106*i), 50.0); //changed from y-6 to y-50
            hexagons[i]= pg;
        }
    }
    public void paint(Graphics g){
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, xSize, ySize);
        g.setColor(Color.RED);
        //spacing+(size+space)*i
        for (int i = 0;i<4;i++){
            //g.fillRect(6+(106)*i, 6, 100, 100);
            hexagons[i].drawHexagon(g);
        }
        g.setColor(Color.BLUE);
        for (int i = 0;i<4;i++){
            g.drawImage(tokens.get(i).getImage(),131,200+25+(106*i),50,50,null);
            //g.fillOval(131, 200+25+(106)*i, 50, 50);
        }
        g.setFont(new Font("Arial", 100, 50));
        g.drawString("Player 1:",10,70);
        paintComponents(g);
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
            tokens.add(new WildlifeTokens(4));
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
    public void mousePressed(MouseEvent e) {
        for (int i = 0;i<4;i++){
            
        }
        repaint();
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public int getXPos(){return xPos;}
    public int getYPos(){return yPos;}
    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}
}
