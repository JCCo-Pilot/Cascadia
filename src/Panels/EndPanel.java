package Panels;
import java.util.*;
import javax.swing.*;

import Components.*;
import Entities.WildlifeScoringCards.*;
import EventAndListener.GameListener;
import EventAndListener.GameStateEvent;
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
    private GameListener listener;

    private HashMap<Integer,ArrayList<Integer>>scores = new HashMap<>();

    private ArrayList<Player>players = new ArrayList<>();
    private ArrayList<JButton>playerButtons = new ArrayList<>();
    public EndPanel(){
        setLayout(null);
        repaint();
        construct();
        pullImages();
        this.setVisible(true);
    }
    public void pullImages(){
        try{
            bg = ImageIO.read(Objects.requireNonNull(EndPanel.class.getResource("/Panels/Background/EndScreenbg.png")));
        }catch(Exception e){
            out.println("Error in pulling images in EndPanel class");
        }
    }
    public void construct(){
        for (int i =0;i<players.size();i++){
            JButton temp = new JButton("Player "+(i+1));
            int x = 1010; int yBonus = 0;
            if (i%2==1){x= 1290;}else{x = 1010;}
            if (i<2){yBonus = 0;}else{yBonus = 50;}
            temp.setBounds(x,700+(yBonus),275,50);
            temp.setVisible(true);
            temp.addActionListener(this);
            temp.setFocusable(false);
            this.add(temp);
            playerButtons.add(temp);
        }
        this.setVisible(true);
    }
    public void setNumPlayers(int i){
        numPlayers = i;
    }
    public void setPlayers(ArrayList<Player>p){
        players = p;
        construct();
    }
    public Player findMax() {
    	Player max = players.get(0);
    	for(int i = 0; i < players.size(); i++) {
    		if(players.get(i).compareTo(max) > 0) {
    			max = players.get(i);
    		}
    	}
    	PrintTester.print(max.getName());
    	return max;
    }
    private void score(){
        ArrayList<ScoringCard> cards = new ArrayList<ScoringCard>();
        cards.add(bearCard);
        cards.add(foxCard);
        cards.add(elkCard);
        cards.add(hawkCard);
        cards.add(salmonCard);
        Scorer.score(players, cards);
        switch(numPlayers){
            case 4:

            case 3:

            case 2:
                
            break;
        }
    }
    private ArrayList<Integer> scorePlayer(Player p){
        return null;
    }
    public void paint(Graphics g){
        g.drawImage(bg, 0,0, 1585, 865,null);
        Painting(g);
        paintCards(g);
        paintComponents(g);
        g.setFont(new Font("Arial", 10, 100));
        String w = findMax().getName();
        g.drawString(w, 80, 400);
        out.println("Painted");
    }

    public void paintCards(Graphics g){
        g.drawImage(bearCard.getImage(),1020,10,250,230,null);
        g.drawImage(elkCard.getImage(),1300,10,250,230,null);
        g.drawImage(salmonCard.getImage(), 1020, 240, 250, 230, null);
        g.drawImage(hawkCard.getImage(), 1300, 240, 250, 230, null);
        g.drawImage(foxCard.getImage(), 1020, 470, 250, 230, null);
    }

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
        for (int i =0;i<comps.size();i++){
            this.add(comps.get(i));
        }
    }
    public void actionPerformed(ActionEvent e){
        //buttons logic here
        for (int i =0;i<playerButtons.size();i++){
            if (e.getSource()==playerButtons.get(i)){
                GameStateEvent gse = new GameStateEvent(this, players.get(i));
                listener.process(gse);
            }
        }
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
    public void setListener(GameListener gsl){
        listener = gsl;
    }
}
