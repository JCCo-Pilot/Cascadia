package Panels;
import Entities.*;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import MathHelper.*;
import static java.lang.System.*;
public class PopPanel extends JComponent implements MouseListener, ActionListener{
    private Player p;
    private int state = -1;
    private JButton back = new JButton("Back Button");
    private boolean goBack;
    private GameListener listener;
    public PopPanel(){
        super();
        this.setVisible(true);
        back.setBounds(1000,500,200,100);
        back.addActionListener(this);
        back.setVisible(true);
        add(back);
        goBack = false;
        System.out.println(goBack);
    }
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.BLACK);
        p.drawInventory(g, false);
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


