
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import Entities.Player;
import EventAndListener.*;
import Panels.*;
import static java.lang.System.*;

public class App extends JFrame implements GameListener{
    public static void main(String[] args) throws Exception {
        App  ap = new App("Team Aditya Chen");
    }
    private StartPanel spanel;
    private MainPanel mpanel;
    private EndPanel epanel;
    private PopPanel pPanel;
    private PopPanel pPanel2;
    private PlayThroughPanel ptp;
    private boolean gameEnd = false;
    //stuff
    private static final boolean onlyACards= false;
    //testing constructor
    public App(String s,Boolean b){
        super(s);
        this.setSize(1600,900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        mpanel = new MainPanel('a');
        mpanel.setListener(this);
        add(mpanel);
        try {
            this.setIconImage(ImageIO.read(Objects.requireNonNull(App.class.getResource("Entities/Images/IMG_5104.jpg"))));
        } catch (Exception e) {
            // TODO: handle exception
        }
        this.setVisible(true);
    }
    //real constructor
    public App(String s){
        super(s);
        this.setSize(1600,900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        spanel = new StartPanel(onlyACards);
        spanel.setListener(this);
        add(spanel);
        try {
            this.setIconImage(ImageIO.read(Objects.requireNonNull(App.class.getResource("Entities/Images/IMG_5104.jpg"))));
        } catch (Exception e) {
            // TODO: handle exception
        }
        pPanel = new PopPanel(1);
        pPanel2 = new PopPanel(2);

        this.setVisible(true);
    }
    public void process(GameStateEvent e) {
        if (e.getSource()==ptp){
            this.remove(ptp);
            spanel = new StartPanel(onlyACards);
            this.add(spanel);
            spanel.setListener(this);
            repaint();
            this.setVisible(true);
        }else if (e.getSource()==pPanel&&gameEnd){
            out.println("no");
            this.remove(pPanel);
            this.add(epanel);
            repaint();
            this.setVisible(true);
        }else if (e.getSource()==epanel){
            out.println("Yes");
            this.remove(epanel);
            this.add(pPanel);
            pPanel.currentPlayer(e.getPlayer());
            pPanel.setListener(this);
            pPanel.setBearCard(mpanel.getBearCard());
            pPanel.setElkCard(mpanel.getElkCard());
            pPanel.setSalmonCard(mpanel.getSalmonCard());
            pPanel.setHawkCard(mpanel.getHawkCard());
            pPanel.setFoxCard(mpanel.getFoxCard());
            repaint();
            this.setVisible(true);
        }else if(e.getSource()==spanel){
            if(e.getState() == 100) {
        		this.remove(spanel);
        		ptp = new PlayThroughPanel();
        		this.add(ptp);
        		ptp.setListener(this);
        		repaint();
        		this.setVisible(true);
        	}
        	else {
                this.remove(spanel);
                mpanel = new MainPanel(e.getState()+1,e.getDifficulty());
                this.setSize(1900,900);
                this.add(mpanel);
                mpanel.setListener(this);
                repaint();
                this.setVisible(true);
        	}
        }else if (e.getSource()==mpanel){
            this.remove(mpanel);
            this.setSize(1600,900);
            epanel = new EndPanel();
            this.add(epanel);
            epanel.setListener(this);
            
            epanel.setBearCard(mpanel.getBearCard());
            epanel.setElkCard(mpanel.getElkCard());
            epanel.setSalmonCard(mpanel.getSalmonCard());
            epanel.setHawkCard(mpanel.getHawkCard());
            epanel.setFoxCard(mpanel.getFoxCard());

            epanel.setNumPlayers(mpanel.getNumPlayers());
            epanel.setPlayers(mpanel.getPlayers());
            gameEnd = true;
            epanel.repaint();
        }else if (!gameEnd){
            //Find the correct player here
            switch (e.getState()) {
                case 10://player pop ups 1.2.3.4
                    this.remove(mpanel);
                    this.setSize(1600,900);
                    add(pPanel);
                    pPanel.currentPlayer(getNumber(1, mpanel.getPlayers()));
                    pPanel.setListener(this);
                    pPanel.setBearCard(mpanel.getBearCard());
                    pPanel.setElkCard(mpanel.getElkCard());
                    pPanel.setSalmonCard(mpanel.getSalmonCard());
                    pPanel.setHawkCard(mpanel.getHawkCard());
                    pPanel.setFoxCard(mpanel.getFoxCard());
                    repaint();
                    this.setVisible(true);
                break;
                case 20:
                    this.remove(mpanel);
                    this.setSize(1600,900);
                    add(pPanel);
                    pPanel.currentPlayer(getNumber(2, mpanel.getPlayers()));
                    pPanel.setListener(this);
                    pPanel.setBearCard(mpanel.getBearCard());
                    pPanel.setElkCard(mpanel.getElkCard());
                    pPanel.setSalmonCard(mpanel.getSalmonCard());
                    pPanel.setHawkCard(mpanel.getHawkCard());
                    pPanel.setFoxCard(mpanel.getFoxCard());
                    repaint();
                    this.setVisible(true);
                break; 
                case 30:
                    this.remove(mpanel);
                    this.setSize(1600,900);
                    add(pPanel);
                    pPanel.currentPlayer(getNumber(3, mpanel.getPlayers()));
                    pPanel.setListener(this);
                    pPanel.setBearCard(mpanel.getBearCard());
                    pPanel.setElkCard(mpanel.getElkCard());
                    pPanel.setSalmonCard(mpanel.getSalmonCard());
                    pPanel.setHawkCard(mpanel.getHawkCard());
                    pPanel.setFoxCard(mpanel.getFoxCard());
                    repaint();
                    this.setVisible(true);
                break;
                case 40:
                    this.remove(mpanel);
                    this.setSize(1600,900);
                    add(pPanel);
                    pPanel.currentPlayer(getNumber(4, mpanel.getPlayers()));
                    pPanel.setListener(this);
                    pPanel.setBearCard(mpanel.getBearCard());
                    pPanel.setElkCard(mpanel.getElkCard());
                    pPanel.setSalmonCard(mpanel.getSalmonCard());
                    pPanel.setHawkCard(mpanel.getHawkCard());
                    pPanel.setFoxCard(mpanel.getFoxCard());
                    repaint();
                    this.setVisible(true);
                break;
                case 50:
                	this.remove(pPanel);
                    pPanel = new PopPanel(1);
                	add(mpanel);
                    this.setSize(1900,900);
                	mpanel.setListener(this);
                	pPanel.setBearCard(mpanel.getBearCard());
                    pPanel.setElkCard(mpanel.getElkCard());
                    pPanel.setSalmonCard(mpanel.getSalmonCard());
                    pPanel.setHawkCard(mpanel.getHawkCard());
                    pPanel.setFoxCard(mpanel.getFoxCard());
                	repaint();
                    this.setVisible(true);
                break;
                case 60:
                	this.remove(mpanel);
                    pPanel2 = new PopPanel(2);
                	add(pPanel2);
                    pPanel2.currentPlayers(mpanel.getPlayers());
                	pPanel2.setListener(this);
                	pPanel2.setBearCard(mpanel.getBearCard());
                    pPanel2.setElkCard(mpanel.getElkCard());
                    pPanel2.setSalmonCard(mpanel.getSalmonCard());
                    pPanel2.setHawkCard(mpanel.getHawkCard());
                    pPanel2.setFoxCard(mpanel.getFoxCard());
                	repaint();
                	this.setVisible(true);
                break;
            }
        }
    }
    public Player getNumber(int find, ArrayList<Player>players){
        for(int i =0;i<players.size();i++){
            if (players.get(i).getName().equals("Player "+find)){
                return players.get(i);
            }
        }
        return null;
    }
}
