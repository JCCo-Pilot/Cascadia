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
public class PopPanel extends JComponent implements MouseListener{
    private Player p;
    private int state = -1;
    public PopPanel(){
        super();
        repaint();
        this.setVisible(true);
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
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}


