package Panels;
import java.util.*;
import javax.swing.*;

import Components.*;
import Entities.WildlifeScoringCards.*;
import Entities.*;
import Entities.Enums.CardTypes;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;

import static java.lang.System.*;
public class EndPanel extends JPanel implements ActionListener,MouseListener{
    private BufferedImage bg;
    
    private BearCard bearCard;
    private ElkCard elkCard;
    private SalmonCard salmonCard;
    private HawkCard hawkCard;
    private FoxCard foxCard;

    private int numPlayers;

    private HashMap<Integer,ArrayList<Integer>>scores = new HashMap<>();

    private ArrayList<Player>players = new ArrayList<>();
    private ArrayList<JButton>playerButtons = new ArrayList<>();
    public EndPanel(){
        setLayout(null);
        repaint();
        /*for (int i =0;i<4;i++){
            players.add(new Player(i));
        }*/
        construct();
        pullImages();
        this.setVisible(true);
    }
    public void pullImages(){
        try{
            bg = ImageIO.read(new File("src/Panels/Background/EndScreenbg.png"));
        }catch(Exception e){
            out.println("Error in pulling images in EndPanel class");
        }
    }
    public void construct(){
        //coordinates for buttons x - 1020 y - 690, 740, 790, 840 w - 560 h- 50
        //out.println(players.size());
        for (int i =0;i<players.size();i++){
            JButton temp = new JButton("Player "+(i+1));
            temp.setBounds(1020,700+(i*50),560,50);
            temp.setVisible(true);
            temp.addActionListener(this);
            temp.setFocusable(false);
            this.add(temp);
            playerButtons.add(temp);
        }
        //this.addAll(playerButtons);
        this.setVisible(true);
        //testCreateCards();
    }
    public void setNumPlayers(int i){
        numPlayers = i;
    }
    public void setPlayers(ArrayList<Player>p){
        players = p;
        construct();
    }
    private void score(){
        ArrayList<ScoringCard> cards = new ArrayList<ScoringCard>();
        //add all the cards to this
        cards.add(bearCard);
        cards.add(foxCard);
        cards.add(elkCard);
        cards.add(hawkCard);
        cards.add(salmonCard);
        //use the player.getScore() method to return the final score
        //use the player.getScore(CardAnimals) for a card
        //use player.getScore(Habitats) for a habitat
        //use player.getBonus(Habitats) for a habitat bonus
        Scorer.score(players, cards);
        switch(numPlayers){
            case 4:

            case 3:

            case 2:
                
            break;
        }
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
    private ArrayList<Integer> scorePlayer(Player p){
        return null;
    }
    public void paint(Graphics g){
        //g.fillRect(0, 0, 1590, 865);
        g.drawImage(bg, 0,0, 1585, 865,null);
        Painting(g);
        paintCards(g);
        paintComponents(g);
        out.println("Painted");
    }
    public void paintCards(Graphics g){
        g.drawImage(bearCard.getImage(),1020,10,250,230,null);
        g.drawImage(elkCard.getImage(),1300,10,250,230,null);
        g.drawImage(salmonCard.getImage(), 1020, 240, 250, 230, null);
        g.drawImage(hawkCard.getImage(), 1300, 240, 250, 230, null);
        g.drawImage(foxCard.getImage(), 1020, 470, 250, 230, null);
    }
    //painting all the scores of the different players
    public void Painting(Graphics g){
        int size = players.size();
        score();
        //Player Numbers
        g.setFont(new Font("Arial", 10, 30));
        for (int i =0;i<size;i++){
            g.drawString(""+(i+1),765+(60*i),75);
        }
        //Bear drawing stuff
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getScore(Entities.Enums.CardAnimals.BEAR),760+(60*i),130);
        }
        //Elk Drawing stuff
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getScore(Entities.Enums.CardAnimals.ELK),760+(60*i),175);
        }
        //Salmon Coordinates
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getScore(Entities.Enums.CardAnimals.SALMON),760+(60*i),225);
        }
        //Hawk Coordinates
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getScore(Entities.Enums.CardAnimals.HAWK),760+(60*i),275);
        }
        //Fox Coordinates
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getScore(Entities.Enums.CardAnimals.FOX),760+(60*i),325);
        }
        //Sum Coordinates
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getTotalAnimalScore(),760+(60*i),375);
        }
        //Next Section of stuff
        g.setFont(new Font("Arial",10,25));
        //Mountain scoring
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getScore(Entities.Enums.Habitats.MOUNTAIN),755+(60*i),375+55);
        }
        //Scoring Bonuses
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getBonus(Entities.Enums.Habitats.MOUNTAIN),785+(60*i),375+77);
        }
        //Forest scoring
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getScore(Entities.Enums.Habitats.FOREST),755+(60*i),375+55+50);
        }
        //Scoring Bonus
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getBonus(Entities.Enums.Habitats.FOREST),785+(60*i),375+77+50);
        }
        //Desert scoring
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getScore(Entities.Enums.Habitats.PRAIRIE),755+(60*i),375+55+100);
        }
        //Scoring Bonus
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getBonus(Entities.Enums.Habitats.PRAIRIE),785+(60*i),375+77+100);
        }
        //Swamp scoring
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getScore(Entities.Enums.Habitats.WETLAND),755+(60*i),375+55+150);
        }
        //Scoring Bonus
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getBonus(Entities.Enums.Habitats.WETLAND),785+(60*i),375+77+150);
        }
        //River scoring
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getScore(Entities.Enums.Habitats.RIVER),755+(60*i),375+55+200);
        }
        //Scoring Bonus
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getBonus(Entities.Enums.Habitats.RIVER),785+(60*i),375+77+200);
        }
        //total of habitat tile
        g.setFont(new Font("Arial", 10, 30));
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getTotalHabitatScore(),760+(60*i),375+77+240);
        }
        //Nature Tokens
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getNatureTokens(),755+(60*i),375+77+300);
        }
        //Total Score
        for (int i =0;i<size;i++){
            g.drawString(""+players.get(i).getScore(),755+(60*i),375+77+360);
        }
    }
    private void testCreateCards(){
        CardTypes type = CardTypes.CARD_A;
        bearCard = new BearCard(type);
        foxCard = new FoxCard(type);
        elkCard = new ElkCard(type);
        hawkCard = new HawkCard(type);
        salmonCard = new SalmonCard(type);
    }
    private void addAll(ArrayList<JButton>comps){
        //System.out.println("Line 59"+comps.size());
        for (int i =0;i<comps.size();i++){
            this.add(comps.get(i));
        }
    }
    public void actionPerformed(ActionEvent e){
        
    }
    public void mouseClicked(MouseEvent e) {
        repaint();
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}    
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
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

}
