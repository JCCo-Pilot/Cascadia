package Panels;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import java.awt.event.*;
import java.awt.*;
public class EndPanel extends JPanel implements ActionListener,MouseListener{
    public EndPanel(){
        setLayout(null);
        repaint();
    }
    public void paint(Graphics g){
        g.fillRect(0, 0, 1590, 865);
    }
    public void actionPerformed(ActionEvent e){}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}    
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
