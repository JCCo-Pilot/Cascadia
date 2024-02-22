import java.util.*;
import javax.swing.*;

import Components.*;
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
    public App(String s){
        super(s);
        this.setSize(1600,900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        spanel = new StartPanel();
        spanel.setListener(this);
        add(spanel);
        this.setVisible(true);
    }
    public void process(GameStateEvent e) {
        if(e.getSource()==spanel){
            this.remove(spanel);
            mpanel = new MainPanel();
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
                case 10:
                    out.println("works");
                break;
                case 20:
                    out.println("works1");
                break; 
                case 30:
                    out.println("works2");
                break;
                case 40:
                    out.println("works3"); 
                break;
            }
        }
    }
}
