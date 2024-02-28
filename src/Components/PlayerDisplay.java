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
public class PlayerDisplay extends JComponent implements MouseListener,PickListener{
    private int xSize,ySize;
    private int xPos,yPos;
    private WildlifeTokens token;
    private Integer[] xPositions = {92,213,334,455,576,697,818,153,274,395,516,637,758};
    private ArrayList<HabitatTiles>testHexagons = new ArrayList<>();
    private AllowPickEventListener listener;
    public PlayerDisplay(int x, int y, int xS, int yS){
        super();
        this.setVisible(true);
        xPos = x; yPos = y;
        xSize = xS; ySize = yS;
        constructHexagons();
        enableInputMethods(true);
        addMouseListener(this);
    }
    private void constructHexagons(){//replace all the 50.0 with 100.0
        //y increments are radius*1.5
        //x increments are size* root(3)/2
        int size = 70;
        Double inc = size*Math.sqrt(3)/2;
        int xInc = (int)(Math.round(inc));
        out.println(xInc*2*(0+1)-30);
        
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D antiAlias = (Graphics2D) g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paint(g);

        g.setColor(Color.GREEN);
        g.fillRect(0, 0, xPos, yPos);
    }
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        Polygon p = new Polygon();
        for (int i =0; i<testHexagons.size();i++){
            testHexagons.get(i).drawHexagon(g);
        }
    }
    public Dimension getPreferredSize() {return new Dimension(xSize, ySize);}
    public Dimension getMinimumSize() {return new Dimension(xSize, ySize );}
    public Dimension getMaximumSize() {return new Dimension(xSize , ySize );}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        for(int i =0;i<testHexagons.size();i++){
            if(testHexagons.get(i).isPointInsideHexagon(e)){
                if (token!=null){
                    if (testHexagons.get(i).canPick(token)){
                        //out.println("Why");
                        testHexagons.get(i).addToken(token);
                        AllowPickEvent ape = new AllowPickEvent(this, true);
                        listener.process(ape);
                        token =null;
                    }  
                }
            }
        }
        repaint();
    }
    public void addListener(AllowPickEventListener apel){listener = apel;}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public int getXPos(){return xPos;}
    public int getYPos(){return yPos;}
    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}
    public void process(PickEvent e){
        if (e.getToken()!=null){
            token = e.getToken();
        }else if(e.getTile()!=null){
            HabitatTiles tiles = e.getTile();
            tiles.setX(xPositions[testHexagons.size()]);
            tiles.setY(105);
            testHexagons.add(tiles);
            repaint();
        }
    }
}
