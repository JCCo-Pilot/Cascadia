import java.util.*;
import javax.swing.*;

import Components.*;
import EventAndListener.*;
import Panels.*;
import static java.lang.System.*;
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

        pPanel = new PopPanel();


        this.setVisible(true);
    }
    public void process(GameStateEvent e) {
        if(e.getSource()==spanel){
            this.remove(spanel);
            mpanel = new MainPanel(e.getState()+1);
            this.add(mpanel);
            mpanel.setListener(this);
            repaint();
            this.setVisible(true);
            out.println("Switched");
        }else if (e.getSource()==mpanel){
            this.remove(mpanel);
            epanel = new EndPanel();
            this.add(epanel);
        }else{
            switch (e.getState()) {
                case 10://player pop ups 1.2.3.4
                    this.remove(mpanel);
                    add(pPanel);
                    pPanel.currentPlayer(1);
                    repaint();
                break;
                case 20:
                    this.remove(mpanel);
                    add(pPanel);
                    pPanel.currentPlayer(2);
                    repaint();
                break; 
                case 30:
                    this.remove(mpanel);
                    add(pPanel);
                    pPanel.currentPlayer(3);
                    repaint();
                break;
                case 40:
                    this.remove(mpanel);
                    add(pPanel);
                    pPanel.currentPlayer(4);
                    repaint();
                break;
            }
        }
    }
}
