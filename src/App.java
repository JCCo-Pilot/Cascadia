import java.util.*;
import javax.swing.*;

import EventAndListener.*;
public class App extends JFrame implements GameListener{
    public static void main(String[] args) throws Exception {
        App  ap = new App("String");
    }
    public App(String s){
        super(s);
        this.setSize(1600,900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }
    @Override
    public void process(GameStateEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'process'");
    }
}
