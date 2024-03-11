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
public class MainPanel extends JPanel implements MouseListener,ActionListener{
    private GameListener listener;
    private PickArea pa;
    private PlayerDisplay pd;
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<JButton>buttons = new ArrayList<>();
    private ArrayList<ScoringCard>cards = new ArrayList<>();
    private BufferedImage bg;
    public MainPanel(int l, Character diffcult){
        setLayout(null);

        construct(l);
        constructStarters();

        constructScoring(diffcult);

        pd = new PlayerDisplay(310, 0, 905, 870,players);
        pd.setBounds(pd.getXPos(),pd.getYPos(),pd.getPreferredSize().width,pd.getPreferredSize().height);
        add(pd);

        pa = new PickArea(l,0,0,310,870);
        pa.setBounds(pa.getXPos(),pa.getYPos(),pa.getPreferredSize().width,pa.getPreferredSize().height);
        pa.addListener(pd);
        add(pa);

        pd.addListener(pa);

        try{
            bg = ImageIO.read(new File("src/Panels/Background/MainPanelBG.png"));
        }catch(Exception e){
            out.println("Unable to pull");
        }

        pa.setPlayers(players);
        this.setVisible(true);
    }
    private void constructScoring(Character diff){
        switch(diff){
            case 'a':
                CardTypes type = CardTypes.CARD_A; 
                cards.add(new BearCard(type));
                cards.add(new FoxCard(type));
                cards.add(new ElkCard(type));
                cards.add(new HawkCard(type));
            break;
            case 'b':
                type = CardTypes.CARD_B; 
                cards.add(new BearCard(type));
                cards.add(new FoxCard(type));
                cards.add(new ElkCard(type));
                cards.add(new HawkCard(type));
            break;
            case 'c':
                type = CardTypes.CARD_C; 
                cards.add(new BearCard(type));
                cards.add(new FoxCard(type));
                cards.add(new ElkCard(type));
                cards.add(new HawkCard(type));
            break;
            case'd':
                type = CardTypes.CARD_D; 
                cards.add(new BearCard(type));
                cards.add(new FoxCard(type));
                cards.add(new ElkCard(type));
                cards.add(new HawkCard(type));
            break;
            case 'z':
                Integer[] rands = new Integer[4];
                for (int i =0;i<rands.length;i++){
                    rands[i]=(int)(Math.random()*4);
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
                            cards.add(new BearCard(t));
                        break;
                        case 1:
                            cards.add(new ElkCard(t));
                        break;
                        case 2:
                            cards.add(new FoxCard(t));
                        break;
                        case 3:
                            cards.add(new HawkCard(t));
                        break;
                    }
                    
                }
            break;
        }
    }
    private void construct(int limit){
        //buttons.add(new JButton("Scoring Cards"));
        for (int i= 0;i<limit;i++){
            buttons.add(new JButton("Player "+(i+1)));
        }
        for (int i= 0;i<buttons.size();i++){
            buttons.get(i).setBounds(1213,19+40*i,352,40);
            buttons.get(i).addActionListener(this);
            buttons.get(i).setVisible(true);
            buttons.get(i).setFocusable(false);
            this.add(buttons.get(i));
        }
        //construction time
        for(int i =1;i<5;i++){
            players.add(new Player(i));
        }
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
        sTiles.add(new StarterTile(tiles).setPos(400, 400, 70.0));
        tiles.clear();
        //tile 2
        tiles.add(new HabitatTiles("forest-elk-key", new String[]{"forest"}, new String[]{"elk"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("lake+mountain-hawk-elk-bear", new String[]{"lake","mountain"}, new String[]{"bear","elk","hawk"}, false, 190, 205, size,true));//240
        tiles.get(1).setOffset(240);
        tiles.add(new HabitatTiles("desert+swamp-fox-salmon", new String[]{"desert","swamp"}, new String[]{"fox","salmon"}, false, 310, 205, size,true));//300
        tiles.get(2).setOffset(300);
        sTiles.add(new StarterTile(tiles).setPos(400, 400, 70.0));
        tiles.clear();
        //tile 3
        tiles.add(new HabitatTiles("forest-elk-key", new String[]{"forest"}, new String[]{"elk"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("lake+mountain-hawk-elk-bear", new String[]{"lake","mountain"}, new String[]{"bear","elk","hawk"}, false, 190, 205, size,true));//240
        tiles.get(1).setOffset(240);
        tiles.add(new HabitatTiles("desert+swamp-fox-salmon", new String[]{"desert","swamp"}, new String[]{"fox","salmon"}, false, 310, 205, size,true));//300
        tiles.get(2).setOffset(300);
        sTiles.add(new StarterTile(tiles).setPos(400, 400, 70.0));
        tiles.clear();
        //tile 4
        tiles.add(new HabitatTiles("desert-fox-key", new String[]{"desert"}, new String[]{"fox"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("swamp+lake-salmon-fox-hawk", new String[]{"swamp","lake"}, new String[]{"salmon","fox","hawk"}, false, 190, 205, size,true));//240
        tiles.get(1).setOffset(60);
        tiles.add(new HabitatTiles("mountain+forest-bear-elk", new String[]{"mountain","forest"}, new String[]{"bear","elk"}, false, 310, 205, size,true));//300
        tiles.get(2).setOffset(300);
        sTiles.add(new StarterTile(tiles).setPos(400, 400, 70.0));
        tiles.clear();
        //tile 5
        tiles.add(new HabitatTiles("mountain-bear-key", new String[]{"mountain"}, new String[]{"bear"}, true, 250, 100, size,true));
        tiles.add(new HabitatTiles("forest+swamp-hawk-elk-fox", new String[]{"forest","swamp"}, new String[]{"hawk","elk","fox"}, false, 190, 205, size,true));//240
        tiles.get(1).setOffset(60);
        tiles.add(new HabitatTiles("desert+lake-salmon-bear", new String[]{"mountain","lake"}, new String[]{"salmon","bear"}, false, 310, 205, size,true));//300
        tiles.get(2).setOffset(300);
        sTiles.add(new StarterTile(tiles).setPos(400, 400, 70.0));
        tiles.clear();

        //randomize adding starter tiles for each players
        Collections.shuffle(sTiles);
        for (int i =0;i<players.size();i++){
            players.get(i).add(sTiles.get(i));
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(bg, 0, 0, 1590,865,null);
        /*g.setColor(Color.GREEN);
        for (int i=0;i<4;i++){
            g.drawRect(1213, 19+(40*i), 352, 40);
        }*/
        //g.fillRect(310,0,905,870);
        this.paintComponents(g);
        //pa.paint(g);
        //g.fillRect(700, 100, 500, 500);
    }
    public void setListener(GameListener g){
        listener = g;
    }
    public void actionPerformed(ActionEvent e){
        for (int i =1;i<buttons.size();i++){
            if (e.getSource()==buttons.get(i)){
                GameStateEvent gse = new  GameStateEvent(buttons.get(i), 10*i);
                listener.process(gse);
            }
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        repaint();
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
