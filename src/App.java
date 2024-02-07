import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import Panels.*;
public class App extends JFrame implements GameListener{
    public static void main(String[] args) throws Exception {
        App  ap = new App("String");
    }
    private StartPanel spanel;
    private MainPanel mpanel;
    private EndPanel epanel;
    public App(String s){
        super(s);
        this.setSize(1600,900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        spanel = new StartPanel();
        this.add(spanel);
    }
    public void process(GameStateEvent e) {
        if(e.getSource()==spanel){
            this.remove(spanel);
            mpanel = new MainPanel();
            this.add(mpanel);
        }else if (e.getSource()==mpanel){
            this.remove(mpanel);
            epanel = new EndPanel();
            this.add(epanel);
        }
    }
}
