package Panels;
import java.util.*;
import javax.swing.*;

import Components.scoringDisplay;
import EventAndListener.*;
import java.awt.event.*;
import java.awt.*;
public class EndPanel extends JPanel implements ActionListener,MouseListener{
    private scoringDisplay sd;
    private int numPlayers;
    private ArrayList<JButton>playerButtons = new ArrayList<>();
    public EndPanel(){
        setLayout(null);
        sd = new scoringDisplay(null, 1,0,0,1590,860);// 665,17,355,865
        repaint();
        construct();
    }
    public void construct(){
        //coordinates for buttons x - 1020 y - 690, 740, 790, 840 w - 560 h- 50
        for (int i =0;i<numPlayers;i++){
            JButton temp = new JButton("Player "+(i+1));
            temp.setBounds(1020,690+(i*50),560,50);
            temp.setVisible(true);
            playerButtons.add(temp);
        }
        addAll(playerButtons);
    }
    public void paint(Graphics g){
        //g.fillRect(0, 0, 1590, 865);
        paintComponents(g);
        testPainting(g);
    }
    public void testPainting(Graphics g){
        //scoring cards
        g.setColor(Color.BLUE);
        g.fillRect(665, 17, 355, 830);
        //more scoring stuff
        for (int i =0;i<4;i++){
            if(i%2==1){g.setColor(Color.BLACK);}
            else{g.setColor(Color.BLUE);}
            g.fillRect(1020,645+(i*50),560,50);
        }
        //scoring cards
        //w,h - 220 
        //x-1060,1320,1060,1320 y-40,40,270,270
        g.fillRect(1060, 40, 190+30, 190);
        g.fillRect(1320, 40, 190+30, 190);
        g.fillRect(1060, 240, 190+30, 190);
        g.fillRect(1320, 240, 190+30, 190);
        g.fillRect(1060, 440, 190+30, 190);
    }
    private void addAll(ArrayList<JButton>comps){
        for (int i =0;i<comps.size();i++){
            this.add(comps.get(i));
        }
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
