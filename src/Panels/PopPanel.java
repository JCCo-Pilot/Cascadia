package Panels;
import Entities.*;
import Entities.WildlifeScoringCards.BearCard;
import Entities.WildlifeScoringCards.ElkCard;
import Entities.WildlifeScoringCards.FoxCard;
import Entities.WildlifeScoringCards.HawkCard;
import Entities.WildlifeScoringCards.SalmonCard;
import Entities.WildlifeScoringCards.ScoringCard;

import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;

import MathHelper.*;
import static java.lang.System.*;
public class PopPanel extends JComponent implements MouseListener, ActionListener{
    private Player p;
    private ArrayList<Player> players;
    private int state = -1;
    private JButton back = new JButton("Back Button");
    private boolean goBack;
    private GameListener listener;
    private BufferedImage bg;
    private BufferedImage bg2;
    private BearCard bearCard;
    private ElkCard elkCard;
    private SalmonCard salmonCard;
    private HawkCard hawkCard;
    private FoxCard foxCard;
    public PopPanel(int a){
        super();
        this.setVisible(true);
        pullImages();
        back.setBounds(690,790,150,50);
        back.addActionListener(this);
        back.setVisible(true);
        add(back);
        goBack = false;
        state = a;
    }
    public void paint(Graphics g){
        super.paint(g);
        if(state == 1) {
        	g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
            g.setColor(Color.BLACK);
        	p.drawInventory(g, false);
            //Player Numbers
            g.setFont(new Font("Arial", 10, 30));
            g.drawString(""+p.getName().charAt(p.getName().length()-1),950,75);
            //Bear drawing stuff
            g.drawString(""+p.getScore(Entities.Enums.CardAnimals.BEAR),950,130);
            //Elk Drawing stuff
            g.drawString(""+p.getScore(Entities.Enums.CardAnimals.ELK),950,180);
            //Salmon Coordinates
            g.drawString(""+p.getScore(Entities.Enums.CardAnimals.SALMON),950,230);
            //Hawk Coordinates
            g.drawString(""+p.getScore(Entities.Enums.CardAnimals.HAWK),950,275);
            //Fox Coordinates
            g.drawString(""+p.getScore(Entities.Enums.CardAnimals.FOX),950,325);
            //Sum Coordinates
            g.drawString(""+p.getTotalAnimalScore(),950,375);
            //Next Section of stuff
            g.setFont(new Font("Arial",10,25));
            //Mountain scoring
            g.drawString(""+p.getScore(Entities.Enums.Habitats.MOUNTAIN),945,375+55);
            //Scoring Bonuses
            g.drawString(""+p.getBonus(Entities.Enums.Habitats.MOUNTAIN),978,375+73);
            //Forest scoring
            g.drawString(""+p.getScore(Entities.Enums.Habitats.FOREST),945,375+55+50);
            //Scoring Bonus
            g.drawString(""+p.getBonus(Entities.Enums.Habitats.FOREST),978,375+73+50);
            //Desert scoring
            g.drawString(""+p.getScore(Entities.Enums.Habitats.PRAIRIE),945,375+55+100);
            //Scoring Bonus
            g.drawString(""+p.getBonus(Entities.Enums.Habitats.PRAIRIE),978,375+73+100);
            //Swamp scoring
            g.drawString(""+p.getScore(Entities.Enums.Habitats.WETLAND),945,375+55+150);
            //Scoring Bonus
            g.drawString(""+p.getBonus(Entities.Enums.Habitats.WETLAND),978,375+73+150);
            //River scoring
            g.drawString(""+p.getScore(Entities.Enums.Habitats.RIVER),945,375+55+200);
            //Scoring Bonus
            g.drawString(""+p.getBonus(Entities.Enums.Habitats.RIVER),978,375+73+200);
            //total of habitat tile
            g.setFont(new Font("Arial", 10, 30));
            g.drawString(""+p.getTotalHabitatScore(),950,375+73+240);
            //Nature Tokens
            g.drawString(""+p.getNatureTokens(),950,375+73+300);
            //Total Score
            g.drawString(""+p.getScore(),945,375+77+360);
            paintCards(g);
            paintComponents(g);
        }
        else if(state == 2) {
        	g.drawImage(bg2, 0, 0, getWidth(), getHeight(), null);
            g.setColor(Color.BLACK);
            back.setBounds(1050,790,150,50);
            paintCards(g);
        	paintComponents(g);
        }
    }
    public void paintCards(Graphics g){
        g.drawImage(bearCard.getImage(),1020,10,250,230,null);
        g.drawImage(elkCard.getImage(),1300,10,250,230,null);
        g.drawImage(salmonCard.getImage(), 1020, 240, 250, 230, null);
        g.drawImage(hawkCard.getImage(), 1300, 240, 250, 230, null);
        g.drawImage(foxCard.getImage(), 1020, 470, 250, 230, null);
    }
    public void pullImages(){
        try{
            bg = ImageIO.read(new File("src/Panels/Background/PopPanelBackground.png"));
            bg2 = ImageIO.read(new File("src/Panels/Background/PopPanelBackground2.png"));
        }catch(Exception e){
            out.println("Error in pulling images in PopPanel class");
        }
    }
    public void currentPlayer(Player pl){
        p = pl;
    }
    public void currentPlayers(ArrayList<Player> pls) {
    	players = pls;
    }
    public void playerTesting(){
        Player pl = new Player(0);
    }
    public boolean getBack() {
    	return goBack;
    }
    public void setListener(GameListener g){
        listener = g;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        goBack = true;
        this.setVisible(false);
        GameStateEvent gse = new  GameStateEvent(back, 50);
        listener.process(gse);
    }
  //setter methods
    public void setBearCard(BearCard bc){
        bearCard = bc;
    }
    public void setElkCard(ElkCard ec){
        elkCard = ec;
    }
    public void setSalmonCard(SalmonCard sc){
        salmonCard = sc;
    }
    public void setHawkCard(HawkCard hc){
        hawkCard = hc;
    }
    public void setFoxCard(FoxCard fc){
        foxCard = fc;
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
