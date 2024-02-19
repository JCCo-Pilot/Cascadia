package Panels;
import java.util.*;
import javax.swing.*;
import Entities.*;
import Components.*;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import static java.lang.System.*;
public class MainPanel extends JPanel implements MouseListener{
    private GameListener listener;
    private ScoreBoard scoreBoard;
    private PickArea pa;
    private ArrayList<Player> players;
    public MainPanel(){
        setLayout(null);
        pa = new PickArea(4,0,0,100,100);
        pa.setBounds(0, 0, 100, 100);
        add(pa);
        this.setVisible(true);
    }
    private void construct(){

    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        this.paintComponents(g);
        //pa.paint(g);
        //g.fillRect(700, 100, 500, 500);
    }
    public void setListener(GameListener g){
        listener = g;
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        repaint();
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
