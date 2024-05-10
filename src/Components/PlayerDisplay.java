package Components;
import Entities.*;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.management.timer.Timer;

import java.awt.image.*;
import MathHelper.*;
import Panels.MainPanel;

import static java.lang.System.*;
public class PlayerDisplay extends JComponent implements MouseListener,PickListener,ActionListener,MouseWheelListener,Runnable{
    private int xSize,ySize;
    private int xPos,yPos;
    private WildlifeTokens token;
    //private Integer[] xPositions = {92,213,334,455,576,697,818,153,274,395,516,637,758,92,213,334,455,576,697,818,153,274,395,516,637,758,92,213,334,455,576,697,818,153,274,395,516,637,758};
    private AllowPickEventListener listener;

    private HabitatTiles current;

    private HabitatTiles temp;

    private MainPanel mainPanel;

    private boolean tutorial = false;

    private coordinateGraphGeneration cgg;
    private HabitatTiles toHighlight;
    private JButton rotateButton;
    private JButton rotateCButton;
    private boolean switchTrigger;
    private boolean showEmptyTiles = true;
    private ArrayList<Player>players;
    Thread t;

    private UpdateEventListener uListener;
    public PlayerDisplay(int x, int y, int xS, int yS, ArrayList<Player>play){
        super();
        Selected.push(this);
        switchTrigger = false;
        players = play;
        Selected.currentPlayer = currentPlayer();
        
        rotateButton = new JButton("RotateC");
        rotateButton.setBounds(790, 770, 105, 40);
        rotateButton.setVisible(false);
        rotateButton.addActionListener(this);
        this.add(rotateButton);

        rotateCButton = new JButton("RotateCC");
        rotateCButton.setBounds(685, 770, 105, 40);
        rotateCButton.setVisible(false);
        rotateCButton.addActionListener(this);
        this.add(rotateCButton);
        
        this.setVisible(true);
        xPos = x; yPos = y;
        xSize = xS; ySize = yS;
        constructHexagons();
        enableInputMethods(true);
        addMouseListener(this);
        addMouseWheelListener(this);
        testConstruct();
        t = new Thread(this);
        t.run();
    }
    //testing stuff
    public PlayerDisplay(int x, int y, int xS, int yS, ArrayList<Player>play,boolean b){
        super();
        Selected.push(this);
        switchTrigger = false;
        players = play;
        Selected.currentPlayer = currentPlayer();
        
        rotateButton = new JButton("RotateC");
        rotateButton.setBounds(790, 770, 105, 40);
        rotateButton.setVisible(false);
        rotateButton.addActionListener(this);
        this.add(rotateButton);

        rotateCButton = new JButton("RotateCC");
        rotateCButton.setBounds(685, 770, 105, 40);
        rotateCButton.setVisible(false);
        rotateCButton.addActionListener(this);
        this.add(rotateCButton);
        
        this.setVisible(true);
        xPos = x; yPos = y;
        xSize = xS; ySize = yS;
        constructHexagons();
        enableInputMethods(true);
        addMouseListener(this);

        testConstruct();
        tutorial = b;
    }


    public void addMainPanel(MainPanel p){
        //what the fuck is a listener anyways
        mainPanel = p;
    }

    public Player currentPlayer(){
        return players.get(0);
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
        //out.println(xInc*2*(0+1)-30);
        
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        periodic();
        Graphics2D antiAlias = (Graphics2D) g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paint(g);

        g.setColor(Color.GREEN);
        g.fillRect(0, 0, xPos, yPos);
    }
    public void paint(Graphics g){
        //super.paint(g);
        periodic();
        
        g.setColor(Color.BLACK);
        Polygon p = new Polygon();
        //cgg.paintAll(g);
        players.get(0).drawInventory(g, showEmptyTiles);
        if(token!=null&&temp==null){
            players.get(0).getGraph().highlightCompatibles(g, token);
        }
        
        g.setFont(new Font("Arial",100,30));
        g.drawString("Turns Left: "+players.get(0).getTurn(),30,50);
        try {
            if((showEmptyTiles||(!toHighlight.isEmpty()))){
                toHighlight.drawMouseHighlight(g);
            }
            
        } catch (Exception e) {
            //System.out.println("shit fucked up");
        }
        paintComponents(g);
        
    }
    public Dimension getPreferredSize() {return new Dimension(xSize, ySize);}
    public Dimension getMinimumSize() {return new Dimension(xSize, ySize );}
    public Dimension getMaximumSize() {return new Dimension(xSize , ySize );}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        //PrintTester.print("******************************************* PLAYER DISPLAY CLICK "+e.getX()+", "+e.getY());
        periodic();
        if(e.getButton()==MouseEvent.BUTTON2){
            ((PickArea)listener).middleClick(e);
            return;
        }
        //for(int i =0;i<players.get(0).getHexagons().size();i++){
            //if(players.get(0).getHexagons().get(i).isPointInsideHexagon(e)){
                if (token!=null&&temp==null){
                    HabitatTiles toAddTo = players.get(0).getGraph().bfs(new MathPoint(e.getX(), e.getY()));
                    //add a check method here
                    if (players.get(0).getGraph().addToken(token, new MathPoint(e.getX(), e.getY()))){
                        AllowPickEvent ape = new AllowPickEvent(this, true);
                        listener.process(ape);
                        token =null;
                        if (toAddTo.isKeystone){
                            players.get(0).incrementNature();
                        }
                        players.get(0).getGraph().update();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        Player p = players.remove(0);
                        p.decrement();
                        players.add(p);
                        //players.add(players.remove(0));
                        showEmptyTiles = true;
                        current = null;
                        AllowPickEvent apes = new AllowPickEvent(this, players.get(0));
                        listener.process(apes);
                        if (tutorial!=true){
                            ((PickArea)listener).placement(true);
                            ((PickArea)listener).jasperisadumbass();
                        }
                        //((PickArea)listener).placement(true);
                        //((PickArea)listener).jasperisadumbass();
                    }  
                }
            //}
        //}
        
        //for (int i =0;i<cgg.getHexs().size();i++){
            if(temp!=null){
            //if (cgg.getHexs().get(i).isPointInsideHexagon(e)&&temp!=null){
                //if (canPlace(cgg.getHexs().get(i).getXPos(), cgg.getHexs().get(i).getYPos())){
                    //out.println(cgg.getHexs().get(i).getXPos());
                    //out.println(cgg.getHexs().get(i).getYPos());
                    
                    //temp.setX(cgg.getHexs().get(i).getXPos());
                    //temp.setY(cgg.getHexs().get(i).getYPos());
                    current = temp;
                    if(players.get(0).addTile(current, new MathPoint(e.getX(), e.getY()))){
                        temp = null;
                        showEmptyTiles = false;
                        if (tutorial!=true){
                            ((PickArea)listener).placement(false);
                        }
                        //((PickArea)listener).placement(false);
                        
                    }
                    
               // }
            }
        //}
        if (uListener!=null) {
            	uListener.update(new UpdateEvent(this, players));
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
            int cx = temp.get(i).getXPos();
            int cy = temp.get(i).getYPos();
            //top left
            if (Math.abs((cx-(r32*sz))-x)<2){
                if (Math.abs((cy-(1.5*sz))-y)<2){
                    // it is the top left of the comparision
                    counter++;
                }
            }
            //top right
            if (Math.abs((cx+(r32*sz))-x)<2){
                if (Math.abs((cy-(1.5*sz))-y)<2){
                    // it is the top right of the comparision
                    counter++;
                }
            }
            //direct left
            if (Math.abs((cx-(r3*sz))-x)<2){
                if (Math.abs(cy-y)<2){
                    counter++;
                }
            }
            //direct right
            if (Math.abs((cx+(r3*sz))-x)<2){
                if (Math.abs(cy-y)<2){
                    counter++;
                }
            }
            //bottom left
            if (Math.abs((cx-(r32*sz))-x)<2){
                if (Math.abs((cy+(1.5*sz))-y)<2){
                    // it is the bottom left of the comparision
                    counter++;
                }
            }
            //bottom right
            if (Math.abs((cx+(r32*sz))-x)<2){
                if (Math.abs((cy+(1.5*sz))-y)<2){
                    // it is the bottom right of the comparision
                    counter++;
                }
            }
        }
        //out.println(counter>0);
        return counter>0;
    }

    public HabitatTiles getCurrentTile(){
        return temp;
    }

    public WildlifeTokens getCurrentToken(){
        return token;
    }

    public void addListener(AllowPickEventListener apel){listener = apel;}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public int getXPos(){return xPos;}
    public int getYPos(){return yPos;}
    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}
    public void setupNew(){
        showEmptyTiles = true;
        temp = null;
        token = null;
        current = null;
        toHighlight = null;
        t = new Thread(this);
        t.setPriority(10);
        t.run();
    }
    public void actionPerformed(ActionEvent e){
        periodic();
        if (e.getSource()==rotateButton&&current!=null){
            current.rotate();
            players.get(0).findAndReplace(current);
            repaint();
        }
        if (e.getSource()==rotateCButton&&current!=null){
            current.rotateC();
            players.get(0).findAndReplace(current);
            repaint();
        }
        uListener.update(new UpdateEvent(this, players));
    }
    public void process(PickEvent e){
        if(e.getString()!=null){
            repaint();
        }else if (e.switchTurns()){
            switchTrigger = true;
            setupNew();
            periodic();
            players.get(0).getGraph().update();
            Player p = players.remove(0);
            p.decrement();
            players.add(p);
            out.println("line 244");
            ((PickArea)listener).jasperisadumbass();
            repaint();
        }else if (e.getToken()!=null){
            token = e.getToken();
            repaint();
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
    //method that is periodically called everytime you want a function to work
    private void periodic(){
        //check to see if the rotate buttons need to be visible
        if(current==null){
            rotateButton.setVisible(false);
            rotateCButton.setVisible(false);
        }
        else if (current!=null&&showEmptyTiles==false){
            rotateButton.setVisible(true);
            rotateCButton.setVisible(true);
        }
        try {
            PrintTester.print("update buttons called by player display");
            mainPanel.updateButtons();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void setUListener(UpdateEventListener uel){
        uListener = uel;
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        //System.out.println("Mouse Wheel Event");
        int units = 0;
        if(current!=null){
            if(e.getWheelRotation()>0){
                units = e.getScrollAmount()/3;
            }else{
                units = -1*e.getScrollAmount()/3;
            }
            
            //System.out.println("units = "+units);
            if(units>0){
                for(int i = 0; i<units; i++){
                    current.rotate();
                }
            }else{
                int negUnits = -1*units;
                for(int i = 0; i<negUnits; i++){
                    current.rotateC();
                }
            }
        }
        repaint();
    }
    @Override
    public void run() {
        Runnable Philip = new Runnable() {
            public void run() {
                Point point = MouseInfo.getPointerInfo().getLocation();
                Point location = PlayerDisplay.this.getLocation();
                int x = (int) (point.getX()-location.getX());
                int y = (int) (point.getY()-location.getY());
                toHighlight = players.get(0).getGraph().bfs(new MathPoint(x, y));
                PlayerDisplay.this.repaint();
            }
        };
        
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(Philip, 0, (int)(Math.floor(Math.pow(players.get(0).getGraph().iterate().size()-2, 2)/10)), TimeUnit.MILLISECONDS);
    }
}
