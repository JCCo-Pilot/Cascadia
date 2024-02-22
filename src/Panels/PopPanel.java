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
    private int state = -1;
    public PopPanel(){
        super();
        this.setVisible(true);
        
    }
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1600, 900);
    }
    public void currentPlayer(int i){
        state =i;
        out.println(i);
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}

