package Panels;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
public class MainPanel extends JPanel implements MouseListener,ActionListener,EndGameListener,UpdateEventListener{
    private GameListener listener;
    private PickArea pa;
    private PlayerDisplay pd;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<JButton>buttons = new ArrayList<>();
    //private ArrayList<ScoringCard>cards = new ArrayList<>();
    private BearCard bearCard;
    private ElkCard elkCard;
    private SalmonCard salmonCard;
    private HawkCard hawkCard;
    private FoxCard foxCard;
    private BufferedImage bg;

    private BufferedImage troll;
    
    //additional fixes
    private ArrayList<miniMap>maps = new ArrayList<>();

    private SelectedScoringCard sc;
    public MainPanel(int l, Character diffcult){
        setLayout(null);

        construct(l);
        constructStarters();

        constructScoring(diffcult);
        
        pd = new PlayerDisplay(310, 15, 905, 830,players);
        pd.setBounds(pd.getXPos(),pd.getYPos(),pd.getPreferredSize().width,pd.getPreferredSize().height);
        add(pd);

        pd.addMainPanel(this);
        pd.setUListener(this);

        pa = new PickArea(l,0,0,310,870);

        pa.setReginaPerez(this);
        pa.setUListener(this);

        pa.setBounds(pa.getXPos(),pa.getYPos(),pa.getPreferredSize().width,pa.getPreferredSize().height);
        pa.addListener(pd);
        add(pa);

        pd.addListener(pa);

        try{
            //ImageIO.read(Reworking.class.getResource("/Image/BackgroundStart.png"));
            bg = ImageIO.read(MainPanel.class.getResource("/Panels/Background/RealMainPanelBG.png"));
            troll = ImageIO.read(MainPanel.class.getResource("/Entities/Images/IMG_5104.jpg"));
            //bg = ImageIO.read(new File("src/Panels/Background/MainPanelBG.png"));
            //troll = ImageIO.read(new File("src/Entities/Images/IMG_5104.jpg"));
        }catch(Exception e){
            out.println("Unable to pull");
        }
        ArrayList<ScoringCard> cards = new ArrayList<ScoringCard>();
            cards.add(bearCard);
            cards.add(elkCard);
            cards.add(salmonCard);
            cards.add(hawkCard);
            cards.add(foxCard);
        //commented this out for the sake of no testing
        //ScoreTesterPanel p = new ScoreTesterPanel(players, cards);
        sc = new SelectedScoringCard();
        pa.setPlayers(players);
        //additional maths
        miniMap temp = null;
        for (int i =0;i<players.size();i++){
            temp = new miniMap(1590, 20+(215*i));
            temp.setPlayer(players.get(i));
            temp.setYSize(215);
            temp.setBounds(temp.getXPos(),temp.getYPos(),temp.getPreferredSize().width,temp.getPreferredSize().height);
            temp.setVisible(true);
            add(temp);
            maps.add(temp);
        }
        
        repaint();
        
        this.setVisible(true);
        addMouseListener(this);
    }
    //test mode
    public MainPanel(Character diffcult){
        setLayout(null);

        construct(1);
        constructStarters();

        constructScoring(diffcult);
        
        pd = new PlayerDisplay(310, 15, 905, 830,players);
        pd.setBounds(pd.getXPos(),pd.getYPos(),pd.getPreferredSize().width,pd.getPreferredSize().height);
        add(pd);

        pa = new PickArea(1,0,0,310,870);

        pa.setReginaPerez(this);

        pa.setBounds(pa.getXPos(),pa.getYPos(),pa.getPreferredSize().width,pa.getPreferredSize().height);
        pa.addListener(pd);
        add(pa);

        pd.addListener(pa);

        try{
            bg = ImageIO.read(MainPanel.class.getResource("/Panels/Background/RealMainPanelBG.png"));
            troll = ImageIO.read(MainPanel.class.getResource("/Entities/Images/IMG_5104.jpg"));
            //bg = ImageIO.read(new File("src/Panels/Background/MainPanelBG.png"));
            //troll = ImageIO.read(new File("src/Entities/Images/IMG_5104.jpg"));
        }catch(Exception e){
            out.println("Unable to pull");
        }
        ArrayList<ScoringCard> cards = new ArrayList<ScoringCard>();
            cards.add(bearCard);
            cards.add(elkCard);
            cards.add(salmonCard);
            cards.add(hawkCard);
            cards.add(foxCard);
        //commented this out for the sake of no testing
        //ScoreTesterPanel p = new ScoreTesterPanel(players, cards);
        for(int i =0;i<1000;i++){
            players.get(0).incrementNature();
        }
        sc = new SelectedScoringCard();
        pa.setPlayers(players);
        this.setVisible(true);
        addMouseListener(this);
    }
    //end of test
    private void constructScoring(Character diff){
        switch(diff){
            case 'a':
                CardTypes type = CardTypes.CARD_A; 
                bearCard = new BearCard(type);
                foxCard = new FoxCard(type);
                elkCard = new ElkCard(type);
                hawkCard = new HawkCard(type);
                salmonCard = new SalmonCard(type);
            break;
            case 'b':
                type = CardTypes.CARD_B; 
                bearCard = new BearCard(type);
                foxCard = new FoxCard(type);
                elkCard = new ElkCard(type);
                hawkCard = new HawkCard(type);
                salmonCard = new SalmonCard(type);
            break;
            case 'c':
                type = CardTypes.CARD_C; 
                bearCard = new BearCard(type);
                foxCard = new FoxCard(type);
                elkCard = new ElkCard(type);
                hawkCard = new HawkCard(type);
                salmonCard = new SalmonCard(type);
            break;
            case'd':
                type = CardTypes.CARD_D; 
                bearCard = new BearCard(type);
                foxCard = new FoxCard(type);
                elkCard = new ElkCard(type);
                hawkCard = new HawkCard(type);
                salmonCard = new SalmonCard(type);
            break;
            case 'z':
                Integer[] rands = new Integer[5];
                for (int i =0;i<rands.length;i++){
                    rands[i]=(int)(Math.random()*5);
                }
                for (int i =0;i<rands.length;i++){
                    int r = rands[i];
                    CardTypes t = CardTypes.CARD_A;
                    switch(r){
                        case 0:
                            t = CardTypes.CARD_A;
                        break;
                        case 1:
                            t = CardTypes.CARD_B;
                        break;
                        case 2:
                            t = CardTypes.CARD_C;
                        break;
                        case 3:
                            t = CardTypes.CARD_D;
                        break;
                    }
                    switch(i){
                        case 0:
                            bearCard = (new BearCard(t));
                        break;
                        case 1:
                            elkCard = (new ElkCard(t));
                        break;
                        case 2:
                            foxCard =(new FoxCard(t));
                        break;
                        case 3:
                            hawkCard = (new HawkCard(t));
                        break;
                        case 4:
                            salmonCard = (new SalmonCard(t));
                        break;
                    }
                    
                }
            break;
        }
    }
    private void construct(int limit){
        //buttons.add(new JButton("Scoring Cards"));
        for (int i= 0;i<limit;i++){
            JButton j = new JButton("Player "+(i+1)+" [0]");
            j.setFont(new Font("Arial", Font.BOLD, 19));
            buttons.add(j);
        }
        //JButton j = new JButton("Show all Players");
        //j.setFont(new Font("Arial", Font.BOLD, 19));
        //buttons.add(j);
        for (int i= 0;i<buttons.size();i++){
            buttons.get(i).setBounds(1217,19+40*i,352,40);
            buttons.get(i).addActionListener(this);
            buttons.get(i).setVisible(true);
            buttons.get(i).setFocusable(false);
            this.add(buttons.get(i));
        }
        //construction time
        for(int i =1;i<limit+1;i++){
            players.add(new Player(i));
        }
    }


    public void updateButtons(){
        ArrayList<ScoringCard> cards = new ArrayList<ScoringCard>();
            cards.add(bearCard);
            cards.add(elkCard);
            cards.add(salmonCard);
            cards.add(hawkCard);
            cards.add(foxCard);
        Scorer.score(players, cards);
        for(int i = 0; i<buttons.size(); i++){
            buttons.get(i).setText("Player "+(i+1)+" ["+findPlayer(i+1, players).getScore()+"]");
        }
        for(Player p:players){
            for(CardAnimals c:CardAnimals.values()){
                System.out.println(p.getName()+", "+c+": "+p.getScore(c));
            }
            for(Habitats h:Habitats.values()){
                System.out.println(p.getName()+", "+h+" SCORE: "+p.getScore(h));
                System.out.println(p.getName()+", "+h+" BONUS: "+p.getBonus(h));
            }
            System.out.println(p.getName()+"NATURE TOKENS: "+p.getNatureTokens());
            System.out.println(p.getName()+"SCORE: "+p.getScore());
        }
    }

    public static Player findPlayer(int find,ArrayList<Player>players){
        for(int i =0;i<players.size();i++){
            if (players.get(i).getName().equals("Player "+find)){
                return players.get(i);
            }
        }
        return null;
    }

    //construct the starter tiles
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
        tiles.clear();
        //tile 2
        tiles.add(new HabitatTiles("forest-elk-key", new String[]{"forest"}, new String[]{"elk"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("lake+mountain-hawk-elk-bear", new String[]{"lake","mountain"}, new String[]{"bear","elk","hawk"}, false, 190, 205, size,true));//240
        tiles.get(1).setOffset(240);
        tiles.add(new HabitatTiles("desert+swamp-fox-salmon", new String[]{"desert","swamp"}, new String[]{"fox","salmon"}, false, 310, 205, size,true));//300
        tiles.get(2).setOffset(300);
        sTiles.add(new StarterTile(tiles).setPos(438, 456, 70.0));
        tiles.clear();
        //tile 3
        tiles.add(new HabitatTiles("forest-elk-key", new String[]{"forest"}, new String[]{"elk"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("lake+mountain-hawk-elk-bear", new String[]{"lake","mountain"}, new String[]{"bear","elk","hawk"}, false, 190, 205, size,true));//240
        tiles.get(1).setOffset(240);
        tiles.add(new HabitatTiles("desert+swamp-fox-salmon", new String[]{"desert","swamp"}, new String[]{"fox","salmon"}, false, 310, 205, size,true));//300
        tiles.get(2).setOffset(300);
        sTiles.add(new StarterTile(tiles).setPos(438, 456, 70.0));
        tiles.clear();
        //tile 4
        tiles.add(new HabitatTiles("desert-fox-key", new String[]{"desert"}, new String[]{"fox"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("swamp+lake-salmon-fox-hawk", new String[]{"swamp","lake"}, new String[]{"salmon","fox","hawk"}, false, 190, 205, size,true));//240
        tiles.get(1).setOffset(60);
        tiles.add(new HabitatTiles("mountain+forest-bear-elk", new String[]{"mountain","forest"}, new String[]{"bear","elk"}, false, 310, 205, size,true));//300
        tiles.get(2).setOffset(300);
        sTiles.add(new StarterTile(tiles).setPos(438, 456, 70.0));
        tiles.clear();
        //tile 5
        tiles.add(new HabitatTiles("mountain-bear-key", new String[]{"mountain"}, new String[]{"bear"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("forest+swamp-hawk-elk-fox", new String[]{"forest","swamp"}, new String[]{"hawk","elk","fox"}, false, 190, 205, size,true));//240
        tiles.get(1).setOffset(60);
        tiles.add(new HabitatTiles("desert+lake-salmon-bear", new String[]{"desert","lake"}, new String[]{"salmon","bear"}, false, 310, 205, size,true));//300
        tiles.get(2).setOffset(300);
        sTiles.add(new StarterTile(tiles).setPos(438, 456, 70.0));
        tiles.clear();

        //randomize adding starter tiles for each players
        Collections.shuffle(sTiles);
        //issues here @JC-Copilot
        for (int i =0;i<players.size();i++){
            players.get(i).add(sTiles.get(i));
            Player temp = players.get(i);
            temp.addAll(sTiles.get(i).getTiles());
            players.set(i,temp);
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(bg, 0, 0, 1890,865,null);
        /*g.setColor(Color.GREEN);
        for (int i=0;i<4;i++){
            g.drawRect(1213, 19+(40*i), 352, 40);
        }*/
        //g.fillRect(310,0,905,870);
        this.paintComponents(g);
        //pa.paint(g);
        //g.fillRect(700, 100, 500, 500);
        g.drawImage(bearCard.getImage(), 1213,250,175,170,null);
        g.drawImage(foxCard.getImage(), 1213+180,250,175,170,null);
        g.drawImage(elkCard.getImage(), 1213,250+180,175,170,null);
        g.drawImage(hawkCard.getImage(), 1213+180,250+180,175,170,null);
        g.drawImage(salmonCard.getImage(), 1213,250+180+180,175,170,null);
        //g.fillRect(1600, 0, 300, 270);
        //g.drawImage(troll,0,0,1600,900,null);
    }
    public ArrayList<Player> getPlayers(){
    	return players;
    }
    public void setListener(GameListener g){
        listener = g;
    }
    public void actionPerformed(ActionEvent e){
        out.println("Errors in clicking buttons");
        for (int i =0;i<buttons.size();i++){
            if (e.getSource()==buttons.get(i)){
                //out.println("Fuck ");
                GameStateEvent gse = new  GameStateEvent(buttons.get(i), 10*(i+1));
                listener.process(gse);
            }
        }
    }
    public void endGameTime(EndGameEvent e){
        GameStateEvent gse = new GameStateEvent(this,players.get(0));
        listener.process(gse);
        //System.out.println("Skbidi on that toilet");
    }
    public void mouseClicked(MouseEvent e) {
    	int x = e.getX();
        int y = e.getY();
        //left side
        if (x>1213&&x<1213+180){
            //first row
            if (y>200&&y<370){
            	sc.addScoringCard(bearCard);
            }
            //second row
            if (y>380&&y<380+170){
            	sc.addScoringCard(elkCard);
            }
            //third row
            if (y>380+180+180&&y<380+180+180+170){
            	sc.addScoringCard(salmonCard);
            }
        }
        //right side
        if (x>1213+180&&x<1213+180+175){
            //first row
            if (y>200&&y<370){
            	sc.addScoringCard(foxCard);
            }
            //second row
            if(y>380&&y<380+170) {
            	sc.addScoringCard(hawkCard);
            }
        }
        repaint();
    }
    public void mousePressed(MouseEvent e) {
    	
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    //getter methods
    public BearCard getBearCard(){
        return bearCard;
    }
    public ElkCard getElkCard(){
        return elkCard;
    }
    public SalmonCard getSalmonCard(){
        return salmonCard;
    }
    public HawkCard getHawkCard(){
        return hawkCard;
    }
    public FoxCard getFoxCard(){
        return foxCard;
    }
    public Integer getNumPlayers(){
        return players.size();
    }
    public void update(UpdateEvent e){
        ArrayList<Player>temp = e.getPlayers();
        for (int i =0;i<temp.size()&&i<maps.size();i++){
            maps.get(i).setPlayer(getNumero(i, temp));
            maps.get(i).repaint();
        }
    }
    private Player getNumero(int find , ArrayList<Player>play){
        for (int i =0;i<play.size();i++){
            if (play.get(i).getName().equals("Player "+(find+1))){
                return play.get(i);
            }
        }
        return null;
    }
    
}
