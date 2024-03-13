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
public class PlayerDisplay extends JComponent implements MouseListener,PickListener,ActionListener{
    private int xSize,ySize;
    private int xPos,yPos;
    private WildlifeTokens token;
    //private Integer[] xPositions = {92,213,334,455,576,697,818,153,274,395,516,637,758,92,213,334,455,576,697,818,153,274,395,516,637,758,92,213,334,455,576,697,818,153,274,395,516,637,758};
    private AllowPickEventListener listener;

    private HabitatTiles current;

    private HabitatTiles temp;

    private coordinateGraphGeneration cgg;
    
    private JButton rotateButton;
    private boolean switchTrigger;
    private ArrayList<Player>players;
    public PlayerDisplay(int x, int y, int xS, int yS, ArrayList<Player>play){
        super();

        switchTrigger = false;
        players = play;
        
        
        rotateButton = new JButton("Rotate");
        rotateButton.setBounds(790, 770, 105, 70);
        rotateButton.setVisible(true);
        rotateButton.addActionListener(this);
        this.add(rotateButton);
        
        this.setVisible(true);
        xPos = x; yPos = y;
        xSize = xS; ySize = yS;
        constructHexagons();
        enableInputMethods(true);
        addMouseListener(this);

        testConstruct();
    }
    private void testConstruct(){
        cgg = new coordinateGraphGeneration(xSize,ySize);
    }

    private void constructHexagons(){
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
        players.get(0).drawInventory(g);
        cgg.paintAll(g);
        paintComponents(g);
    }
    public Dimension getPreferredSize() {return new Dimension(xSize, ySize);}
    public Dimension getMinimumSize() {return new Dimension(xSize, ySize );}
    public Dimension getMaximumSize() {return new Dimension(xSize , ySize );}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        for(int i =0;i<players.get(0).getHexagons().size();i++){
            if(players.get(0).getHexagons().get(i).isPointInsideHexagon(e)){
                if (token!=null){
                    if (players.get(0).getHexagons().get(i).canPick(token)){
                        players.get(0).getHexagons().get(i).addToken(token);
                        AllowPickEvent ape = new AllowPickEvent(this, true);
                        listener.process(ape);
                        token =null;
                        players.add(players.remove(0));
                    }  
                }
            }
        }

        for (int i =0;i<cgg.getHexs().size();i++){
            if (cgg.getHexs().get(i).isPointInsideHexagon(e)&&temp!=null){
                if (canPlace(cgg.getHexs().get(i).getXPos(), cgg.getHexs().get(i).getYPos())){
                    temp.setX(cgg.getHexs().get(i).getXPos());
                    temp.setY(cgg.getHexs().get(i).getYPos());
                    current = temp;
                    players.get(0).addTile(current);
                    temp = null;
                }
            }
        }
        repaint();
    }
    public boolean canPlace(int x, int y){
        Double r3 = 1.7320508075688772935;
        Double ySpace = 1.5;
        Double r32 = 0.86602540378;
        ArrayList<HabitatTiles>temp = players.get(0).getHexagons();
        Double sz = temp.get(0).getSize();
        //check for overlap
        for (int i =0;i<temp.size();i++){
            if (temp.get(i).getXPos()==x){
                if (temp.get(i).getYPos()==y){
                    return false;
                }
            }
        }
        //check to make sure it is its neighbor
        int counter = 0;
        for (int i =0;i<temp.size();i++){
            //top left
            
            //top right
            
            //direct left
            
            //direct right
            
            //bottom left
            
            //bottom right
        }
        return true;
    }
    public void addListener(AllowPickEventListener apel){listener = apel;}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public int getXPos(){return xPos;}
    public int getYPos(){return yPos;}
    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==rotateButton){
            current.rotate();;
            players.get(0).findAndReplace(current);
            repaint();
        }
    }
    public void process(PickEvent e){
        if(e.getString()!=null){
            repaint();
        }else if (e.switchTurns()){
            switchTrigger = true;
            current= null;
            temp = null;
            //players.add(players.remove(0));
            repaint();
        }else if (e.getToken()!=null){
            token = e.getToken();
        }else if(e.getTile()!=null){
            HabitatTiles tiles = e.getTile();
            temp = tiles;
            /*tiles.setX(xPositions[players.get(0).getHexagons().size()]);
            if (players.get(0).getHexagons().size()<7){
                tiles.setY(105);
            }else if (players.get(0).getHexagons().size()<13){
                tiles.setY(215);
            }else if (players.get(0).getHexagons().size()<20){
                tiles.setY(325);
            }else if (players.get(0).getHexagons().size()<26){
                tiles.setY(435);
            }else if (players.get(0).getHexagons().size()<33){
                tiles.setY(435+110);
            }else if (players.get(0).getHexagons().size()<39){
                tiles.setY(435+220);
            }else if (players.get(0).getHexagons().size()<47){
                tiles.setY(435+330);
            }
            //players.get(0).getHexagons()
            players.get(0).addTile(tiles);
            
            current = tiles;*/
            
            repaint();
        }
    }
}
