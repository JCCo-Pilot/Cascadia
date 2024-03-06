package Panels;
import java.util.*;
import javax.swing.*;

import Components.scoringDisplay;
import EventAndListener.*;
import java.awt.event.*;
import java.awt.*;
public class EndPanel extends JPanel implements ActionListener,MouseListener{
    private scoringDisplay sd;
    public EndPanel(){
        setLayout(null);
        sd = new scoringDisplay(null, 1,0,0,1590,860);// 665,17,355,865
        repaint();
    }
    public void paint(Graphics g){
        //g.fillRect(0, 0, 1590, 865);
        paintComponents(g);
    }
    public void actionPerformed(ActionEvent e){}
    public void mouseClicked(MouseEvent e) {
        repaint();
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}    
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
