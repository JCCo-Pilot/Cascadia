package Panels;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import MathHelper.*;
import Entities.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import Components.*;
import Entities.WildlifeScoringCards.*;
import Entities.Enums.*;
import static java.lang.System.*;
public class PlayThroughPanel extends JPanel implements MouseListener,ActionListener,EndGameListener,AllowPickEventListener{
    private Color highligheter = new Color(255, 255, 143);
    private GameListener listener;
    private PickListener pListener;
    private BufferedImage background;
    private PlayerDisplay pd;
    private ArrayList<WildlifeTokens>tokens = new ArrayList<>();
    private ArrayList<HabitatTiles>habitatTiles = new ArrayList<>();
    public HabitatTiles[]hexagons = new HabitatTiles[4];
    private ArrayList<Player>players = new ArrayList<>();
    private ArrayList<JButton>buttons = new ArrayList<>();

    private JTextArea jta = new JTextArea();

    private int limited = -1;
    private int pickedHex = -1;
    private HabitatTiles taken;
    private WildlifeTokens takenToken;

    private JButton suicideButton = new JButton("Return to game");

    private int state;
    public PlayThroughPanel(){
        setLayout(null);
        addMouseListener(this);
        out.println("Added listener");

        createTokens();
        randShuffle();

        state = 1;

        jta.setBounds(1240,100,300,300);
        jta.setText("Please select a highlighted habitat tile");
        jta.setEditable(false);
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);
        jta.setFont(new Font("Times New Roman",10,20));
        this.add(jta);

        suicideButton.setBounds(1215,780,352,40);
        suicideButton.addActionListener(this);
        this.add(suicideButton);

        players.add(new Player(1));
        habitatTiles= new TileCreator().getTiles();
        for (int i = 0;i<4;i++){
            hexagons[i]= habitatTiles.remove(0);
            hexagons[i].setX(56+69);
            hexagons[i].setY(275+(146*i)-100);
        }

        constructStarters();
        try{
            background = ImageIO.read(new File("src/Panels/Background/MainPanelBG.png"));
        }catch(Exception e){

        }
        //button creation
        for (int i= 0;i<1;i++){
            buttons.add(new JButton("Player "+(i+1)));
        }
        for (int i= 0;i<buttons.size();i++){
            buttons.get(i).setBounds(1215,19+40*i,352,40);
            buttons.get(i).addActionListener(this);
            buttons.get(i).setVisible(true);
            buttons.get(i).setFocusable(false);
            this.add(buttons.get(i));
        }
        //player display
        pd = new PlayerDisplay(310, 15, 905, 830,players,true);
        pd.setBounds(pd.getXPos(),pd.getYPos(),pd.getPreferredSize().width,pd.getPreferredSize().height);
        add(pd);

        pd.addListener(this);

        this.pListener(pd);

        this.setVisible(true);
    }
    private void createTokens(){
        for (int i =0;i<20;i++){
            tokens.add(new WildlifeTokens(CardAnimals.BEAR));
            tokens.add(new WildlifeTokens(CardAnimals.ELK));
            tokens.add(new WildlifeTokens(CardAnimals.SALMON));
            tokens.add(new WildlifeTokens(CardAnimals.HAWK));
            tokens.add(new WildlifeTokens(CardAnimals.FOX));
        }
    }
    private void randShuffle(){
        int numTime = (int)(Math.random()*90)+10;
        for (int i = 0;i<numTime;i++){
            Collections.shuffle(tokens);
        }
    }
    private void constructStarters(){
        ArrayList<HabitatTiles>tiles = new ArrayList<>();
        ArrayList<StarterTile>sTiles = new ArrayList<>();
        Double size = 70.0;
        //tile 1
        tiles.add(new HabitatTiles("swamp-hawk-key", new String[]{"swamp"}, new String[]{"hawk"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("forest+lake-salmon-elk-hawk", new String[]{"forest","lake"}, new String[]{"salmon","elk","hawk"}, false, 190, 205, size,true));//240
        tiles.get(1).setOffset(240);
        tiles.add(new HabitatTiles("mountain+desert-bear-fox", new String[]{"mountain","desert"}, new String[]{"fox","bear"}, false, 310, 205, size,true));//300
        tiles.get(2).setOffset(300);
        sTiles.add(new StarterTile(tiles).setPos(438, 456, 70.0));
        players.get(0).add(sTiles.get(0));
    }
    
    public void paint(Graphics g){
        out.println(background==null);
        if(background!=null){
            g.drawImage(background, 0, 0, 1590,865,null);
        }
        //start painting stuff here
        switch(state){
            case 1: //choosing a habitat tile
                g.setColor(highligheter);
                g.fillRect(56+69-70,175-80,140,600);
            break;
            case 2: //choosing an animal token
                g.setColor(highligheter);
                out.println("case 2 achieved");
                g.fillRect(200,95,70,600);
            break;
            case 3://wait for player to place
            break;
        }
        
        for(int i =0;i<4;i++){
            hexagons[i].drawHexagon(g);
        }
        for (int i = 0;i<4;i++){
            g.drawImage(tokens.get(i).getImage(),131+69,250+(146*i)-100,70,70,null);
            //g.fillOval(131, 200+25+(106)*i, 50, 50);
        }
        updateString();
        paintComponents(g);
    }

    public void updateString(){
        switch(state){
            case 1:
                jta.setText("Please select a highlighted habitat tile");
            break;
            case 2:
                jta.setText("Please select an animal token");
            break;
            case 3:
                jta.setText("Place the habitat tile and animal token down");
            break;
            case 4:
            break;
            case 5:
            break;
            case 6:
            break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==suicideButton){
            GameStateEvent gse = new GameStateEvent(this, "Brain Rot");
            if (listener!=null){
                listener.process(gse);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        out.println("Mouse Pressed");
        switch(state){
            case 1://check to see if the player clicked on any hexagons
                for (int i= 0;i<4;i++){
                    if (hexagons[i].isPointInsideHexagon(e)){
                        taken = hexagons[i];
                        hexagons[i]= habitatTiles.remove(0);
                        pickedHex = i;
                        out.println("taken");
                        state =2;
                    }
                }
            break;
            case 2:
                for (int i =0;i<4;i++){
                    if(pointIsInside(200, 250+(146*i)-100, 70, 70, e)&&i == pickedHex){
                        PickEvent evnet = new PickEvent(e, removeAndReplaceToken(i));
                        pListener.process(evnet);
                        hexagons[pickedHex].setX(56+69);
                        hexagons[pickedHex].setY(175+(146*pickedHex));
                        pickedHex = 0;
                        state = 3;
                        PickEvent pe = new PickEvent(this, taken);
                        pListener.process(pe);
                    } 
                }
            break;
            case 3:
            break;
        }
        repaint();
    }
    private boolean pointIsInside(int x, int y, int xSize, int ySize,MouseEvent e){
        if (e.getX()>x&&e.getX()<x+xSize){
            if (e.getY()>y&&e.getY()<y+ySize){
                return true;
            }
        }
        return false;
    }
    public WildlifeTokens removeAndReplaceToken(Integer index){
        if(index<=3&&index>=0){
            int rand = (int) (Math.random()*tokens.size());
            return tokens.set(index, tokens.remove(rand));
        }
        return null;
    }
    public void setListener(GameListener g){
        listener = g;
    }
    public void pListener(PickListener pL){
        pListener = pL;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

    @Override
    public void endGameTime(EndGameEvent e) {
    }

    @Override
    public void process(AllowPickEvent e) {
       
    }
    
}
