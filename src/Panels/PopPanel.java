package Panels;
import Entities.*;
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
    private int state = -1;
    private JButton back = new JButton("Back Button");
    private boolean goBack;
    private GameListener listener;
    private BufferedImage bg;
    public PopPanel(){
        super();
        this.setVisible(true);
        pullImages();
        back.setBounds(500,700,150,50);
        back.addActionListener(this);
        back.setVisible(true);
        add(back);
        goBack = false;
    }
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.BLACK);
        p.drawInventory(g, false);
        score();
        //Player Numbers
        g.setFont(new Font("Arial", 10, 30));
        g.drawString("",765,75);
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
        g.drawString(""+p.getBonus(Entities.Enums.Habitats.MOUNTAIN),985,375+77);
        //Forest scoring
        g.drawString(""+p.getScore(Entities.Enums.Habitats.FOREST),945,375+55+50);
        //Scoring Bonus
        g.drawString(""+p.getBonus(Entities.Enums.Habitats.FOREST),985,375+77+50);
        //Desert scoring
        g.drawString(""+p.getScore(Entities.Enums.Habitats.PRAIRIE),945,375+55+100);
        //Scoring Bonus
        g.drawString(""+p.getBonus(Entities.Enums.Habitats.PRAIRIE),985,375+77+100);
        //Swamp scoring
        g.drawString(""+p.getScore(Entities.Enums.Habitats.WETLAND),945,375+55+150);
        //Scoring Bonus
        g.drawString(""+p.getBonus(Entities.Enums.Habitats.WETLAND),985,375+77+150);
        //River scoring
        g.drawString(""+p.getScore(Entities.Enums.Habitats.RIVER),945,375+55+200);
        //Scoring Bonus
        g.drawString(""+p.getBonus(Entities.Enums.Habitats.RIVER),985,375+77+200);
        //total of habitat tile
        g.setFont(new Font("Arial", 10, 30));
        g.drawString("",760,375+77+240);
        //Nature Tokens
        g.drawString(""+p.getNatureTokens(),945,375+77+300);
        //Total Score
        g.drawString(""+p.getScore(),945,375+77+360);
        paintComponents(g);
    }
    private void score(){
        ArrayList<Entities.Enums.CardAnimals> cards = new ArrayList<Entities.Enums.CardAnimals>();
        //add all the cards to this
        cards.add(Entities.Enums.CardAnimals.BEAR);
        cards.add(Entities.Enums.CardAnimals.BEAR);
        cards.add(Entities.Enums.CardAnimals.BEAR);
        cards.add(Entities.Enums.CardAnimals.BEAR);
        cards.add(Entities.Enums.CardAnimals.BEAR);
        //use the player.getScore() method to return the final score
        //use the player.getScore(CardAnimals) for a card
        //use player.getScore(Habitats) for a habitat
        //use player.getBonus(Habitats) for a habitat bonus
        /*for (int i =0;i<players.size();i++){
            ArrayList<Integer>temp = new ArrayList<>();
            Player current = players.get(i);
            //bear
            temp.add(bearCard.score(current));
            //elk
            temp.add(elkCard.score(current));
            //salmon
            temp.add(salmonCard.score(current));
            //hawk
            temp.add(hawkCard.score(current));
            //fox
            temp.add(foxCard.score(current));
            //total
            //put it into the hashMap
        }*/
    }
    public void pullImages(){
        try{
            bg = ImageIO.read(new File("src/Panels/Background/PopPanelBackground.png"));
        }catch(Exception e){
            out.println("Error in pulling images in PopPanel class");
        }
    }
    public void currentPlayer(Player pl){
        p = pl;
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
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
