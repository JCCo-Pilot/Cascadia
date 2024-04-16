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
    private BufferedImage background;
    private PlayerDisplay pd;
    public ArrayList<HabitatTiles>habitatTiles = new ArrayList<>();
    public HabitatTiles[]hexagons = new HabitatTiles[4];
    private ArrayList<Player>players = new ArrayList<>();
    private ArrayList<JButton>buttons = new ArrayList<>();

    private int state;
    public PlayThroughPanel(){
        setLayout(null);

        state = 1;

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
            buttons.get(i).setBounds(1213,19+40*i,352,40);
            buttons.get(i).addActionListener(this);
            buttons.get(i).setVisible(true);
            buttons.get(i).setFocusable(false);
            this.add(buttons.get(i));
        }
        //player display
        pd = new PlayerDisplay(310, 15, 905, 830,players);
        pd.setBounds(pd.getXPos(),pd.getYPos(),pd.getPreferredSize().width,pd.getPreferredSize().height);
        add(pd);

        pd.addListener(this);

        this.setVisible(true);
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
        tiles.add(new HabitatTiles("desert+lake-salmon-bear", new String[]{"mountain","lake"}, new String[]{"salmon","bear"}, false, 310, 205, size,true));//300
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
    
    public void paint(Graphics g){
        out.println(background==null);
        if(background!=null){
            g.drawImage(background, 0, 0, 1590,865,null);
        }
        //start painting stuff here
        switch(state){
            case 1: //choosing a habitat tile
                g.setColor(new Color(255, 255, 143));
                g.fillRect(56+69-70,175-80,140,600);
            break;
            case 2: //choosing a nature token
            break;
            case 3://wait for player to place
            break;
        }
        
        for(int i =0;i<4;i++){
            hexagons[i].drawHexagon(g);
        }
        
        paintComponents(g);
    }

    public void drawString(Graphics g){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void endGameTime(EndGameEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'endGameTime'");
    }

    @Override
    public void process(AllowPickEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'process'");
    }
    
}