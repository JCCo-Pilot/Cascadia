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

public class ScoreTesterPanel extends JFrame{
    private static ArrayList<Player> players = new ArrayList<>();
    private static ArrayList<ScoringCard> cards = new ArrayList<>();
    private static HashSet<ScoreTesterPanel> panels = new HashSet<ScoreTesterPanel>();

    
    public ScoreTesterPanel(ArrayList<Player> players, ArrayList<ScoringCard> cards){
        super("Score Tester Panel");
        ScoreTesterPanel.players = players;
        ScoreTesterPanel.cards = cards;
        this.setSize(500, 100);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.add(new testerPanel());
        this.setVisible(true);
    }

    public static void update(){
        Scorer.score(players, cards);
        for(Player p:players){
            for(ScoringCard c:cards){
                System.out.println(p.getName()+", "+c.getAnimal()+": "+p.getScore(c.getAnimal()));
            }
            for(Habitats h:Habitats.values()){
                System.out.println(p.getName()+", "+h+": "+p.getScore(h));
            }
        }
    }

    private class testerPanel extends JPanel{
        testerPanel(){
            super();
            this.setLayout(new GridLayout(5, 15));
        }
        void paint(){
            
        }
    }
}
