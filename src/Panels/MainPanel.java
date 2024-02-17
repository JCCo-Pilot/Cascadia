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
    private ArrayList<Player> players;
    public MainPanel(){

    }
    private void construct(){

    }
    @Override
    public void paint(Graphics g){
        paintComponent(g);
        g.drawRect(0, 0, 500, 500);
    }
    public void setListener(GameListener g){
        listener = g;
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
