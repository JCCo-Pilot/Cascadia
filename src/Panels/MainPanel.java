package Panels;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import MathHelper.*;
import Entities.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import Components.*;
import static java.lang.System.*;
public class MainPanel extends JPanel implements MouseListener,ActionListener{
    private GameListener listener;
    private PickArea pa;
    private PlayerDisplay pd;
    private ArrayList<Player> players;
    private ArrayList<JButton>buttons = new ArrayList<>();
    private BufferedImage bg;
    public MainPanel(int l){
        setLayout(null);

        pd = new PlayerDisplay(310, 0, 905, 870);
        pd.setBounds(pd.getXPos(),pd.getYPos(),pd.getPreferredSize().width,pd.getPreferredSize().height);
        add(pd);

        pa = new PickArea(l,0,0,310,870);
        pa.setBounds(pa.getXPos(),pa.getYPos(),pa.getPreferredSize().width,pa.getPreferredSize().height);
        pa.addListener(pd);
        add(pa);

        try{
            bg = ImageIO.read(new File("src/Panels/Background/MainPanelBG.png"));
        }catch(Exception e){
            out.println("Unable to pull");
        }

        construct(l);
        this.setVisible(true);
    }
    private void construct(int limit){
        buttons.add(new JButton("Scoring Cards"));
        for (int i= 0;i<limit;i++){
            buttons.add(new JButton("Player "+(i+1)));
        }
        for (int i= 0;i<buttons.size();i++){
            buttons.get(i).setBounds(1213,19+40*i,352,40);
            buttons.get(i).addActionListener(this);
            buttons.get(i).setVisible(true);
            buttons.get(i).setFocusable(false);
            this.add(buttons.get(i));
        }
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(bg, 0, 0, 1590,865,null);
        /*g.setColor(Color.GREEN);
        for (int i=0;i<4;i++){
            g.drawRect(1213, 19+(40*i), 352, 40);
        }*/
        //g.fillRect(310,0,905,870);
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
