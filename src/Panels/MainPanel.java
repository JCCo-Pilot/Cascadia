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
public class MainPanel extends JPanel implements MouseListener,ActionListener{
    private GameListener listener;
    private PickArea pa;
    private PlayerDisplay pd;
    private ArrayList<Player> players;
    private ArrayList<JButton>buttons = new ArrayList<>();
    public MainPanel(int l){
        setLayout(null);
        pa = new PickArea(l,0,0,250,870);
        pa.setBounds(pa.getXPos(),pa.getYPos(),pa.getPreferredSize().width,pa.getPreferredSize().height);
        add(pa);

        pd = new PlayerDisplay(250, 0, 1100, 870);
        pd.setBounds(pd.getXPos(),pd.getYPos(),pd.getPreferredSize().width,pd.getPreferredSize().height);
        add(pd);

        construct(l);
        this.setVisible(true);
    }
    private void construct(int limit){
        buttons.add(new JButton("Scoring Cards"));
        for (int i= 0;i<limit;i++){
            buttons.add(new JButton("Player "+(i+1)));
        }
        for (int i= 0;i<buttons.size();i++){
            buttons.get(i).setBounds(1350,0+50*i,250,50);
            buttons.get(i).addActionListener(this);
            buttons.get(i).setVisible(true);
            buttons.get(i).setFocusable(false);
            this.add(buttons.get(i));
        }
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
    public void actionPerformed(ActionEvent e){
        for (int i =1;i<buttons.size();i++){
            if (e.getSource()==buttons.get(i)){
                GameStateEvent gse = new  GameStateEvent(buttons.get(i), 10*i);
                listener.process(gse);
            }
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        repaint();
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
