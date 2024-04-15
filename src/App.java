import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import Components.*;
import Entities.Player;
import EventAndListener.*;
import Panels.*;
import static java.lang.System.*;

import java.io.File;
public class App extends JFrame implements GameListener{
    public static void main(String[] args) throws Exception {
        App  ap = new App("Team Aditya Chen");
        //PanelTester pt = new PanelTester("lmao");
    }
    private StartPanel spanel;
    private MainPanel mpanel;
    private EndPanel epanel;
    private PopPanel pPanel;
    public App(String s){
        super(s);
        this.setSize(1600,900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        spanel = new StartPanel();
        spanel.setListener(this);
        add(spanel);
        //troll comments
        try {
            this.setIconImage(ImageIO.read(new File("src/Entities/Images/Philip.png")));
        } catch (Exception e) {
            // TODO: handle exception
        }
        pPanel = new PopPanel();


        this.setVisible(true);
    }
    public void process(GameStateEvent e) {
        if(e.getSource()==spanel){
            this.remove(spanel);
            mpanel = new MainPanel(e.getState()+1,e.getDifficulty());
            this.add(mpanel);
            mpanel.setListener(this);
            repaint();
            this.setVisible(true);
            //out.println("Switched");
        }else if (e.getSource()==mpanel){
            this.remove(mpanel);
            epanel = new EndPanel();
            this.add(epanel);
            
            epanel.setBearCard(mpanel.getBearCard());
            epanel.setElkCard(mpanel.getElkCard());
            epanel.setSalmonCard(mpanel.getSalmonCard());
            epanel.setHawkCard(mpanel.getHawkCard());
            epanel.setFoxCard(mpanel.getFoxCard());

            epanel.setNumPlayers(mpanel.getNumPlayers());
            epanel.setPlayers(mpanel.getPlayers());

            epanel.repaint();
        }else{
            //Find the correct player here
            switch (e.getState()) {
                case 10://player pop ups 1.2.3.4
                    this.remove(mpanel);
                    add(pPanel);
                    pPanel.currentPlayer(getNumero(1, mpanel.getPlayers()));
                    pPanel.setListener(this);
                    repaint();
                    this.setVisible(true);
                break;
                case 20:
                    this.remove(mpanel);
                    add(pPanel);
                    pPanel.currentPlayer(getNumero(2, mpanel.getPlayers()));
                    pPanel.setListener(this);
                    repaint();
                    this.setVisible(true);
                break; 
                case 30:
                    this.remove(mpanel);
                    add(pPanel);
                    pPanel.currentPlayer(getNumero(3, mpanel.getPlayers()));
                    pPanel.setListener(this);
                    repaint();
                    this.setVisible(true);
                break;
                case 40:
                    this.remove(mpanel);
                    add(pPanel);
                    pPanel.currentPlayer(getNumero(4, mpanel.getPlayers()));
                    pPanel.setListener(this);
                    repaint();
                    this.setVisible(true);
                break;
                case 50:
                	this.remove(pPanel);
                    pPanel = new PopPanel();
                	add(mpanel);
                	mpanel.setListener(this);
                	repaint();
                    this.setVisible(true);
                break;
            }
        }
    }
    public Player getNumero(int find,ArrayList<Player>players){
        for(int i =0;i<players.size();i++){
            if (players.get(i).getName().equals("Player "+find)){
                return players.get(i);
            }
        }
        return null;
    }
}
