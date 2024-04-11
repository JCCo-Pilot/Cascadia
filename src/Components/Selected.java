package Components;
import Entities.*;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import MathHelper.*;
import Panels.MainPanel;

import static java.lang.System.*;
public class Selected extends JFrame implements ActionListener{
    private JPanel panel;
    private JPanel buttonPanel;
    private JLabel tileLabel;
    private JLabel tokenLabel;
    private static Component centerOn;
    private Player currentPlayer;
    JButton hide = new JButton("Hide for this Turn");
    JButton hidePermanent = new JButton("Hide Permanently for Current Player");
    private static HashMap<Player, Point> locationPreferences = new HashMap<Player, Point>();
    private static HashMap<Player, Boolean> visible = new HashMap<Player, Boolean>();
    public Selected(){
        super("Selection");
        super.setBackground(Color.BLACK);
        this.setSize(250, 170);
        this.setLocationRelativeTo(centerOn);
        try {
            this.setIconImage(ImageIO.read(new File("src/Entities/Images/Philip.png")));
        } catch (Exception e) {
            // TODO: handle exception
        }
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setSize(250, 135);
        this.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.setLayout(new GridLayout(1, 2, 3, 10));
        tileLabel = new JLabel();
        tokenLabel = new JLabel();
        tileLabel.setBackground(Color.WHITE);    
        tileLabel.setOpaque(true);
        tokenLabel.setBackground(Color.WHITE);
        tokenLabel.setOpaque(true);
        tileLabel.setVisible(true);
        tokenLabel.setVisible(true);
        //tileLabel.setText("Tile Label");
        //tokenLabel.setText("Token Label");
        tileLabel.setVerticalAlignment(JLabel.CENTER);
        tileLabel.setHorizontalAlignment(JLabel.CENTER);
        tokenLabel.setVerticalAlignment(JLabel.CENTER);
        tokenLabel.setHorizontalAlignment(JLabel.CENTER);
        this.add(panel, BorderLayout.NORTH);
        panel.add(tileLabel);
        panel.add(tokenLabel);
        hide.addActionListener(this);
        hidePermanent.addActionListener(this);
        hidePermanent.setFont(new Font(hidePermanent.getFont().getName(), hidePermanent.getFont().getStyle(),hidePermanent.getFont().getSize()-1));
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.add(hide);
        buttonPanel.add(hidePermanent);
        this.add(buttonPanel, BorderLayout.SOUTH);
        hidePermanent.setSize(buttonPanel.getWidth(), 75);
        buttonPanel.setVisible(true);
        hide.setVisible(true);
        hidePermanent.setVisible(true);
    }

    public void push(HabitatTiles h, WildlifeTokens t){
        tileLabel.setBackground(Color.WHITE);    
        tileLabel.setOpaque(true);
        tokenLabel.setBackground(Color.WHITE);
        tokenLabel.setOpaque(true);
        tileLabel.setVisible(true);
        tokenLabel.setVisible(true);
        //tileLabel.setText("Tile Label");
        //tokenLabel.setText("Token Label");
        tileLabel.setVerticalAlignment(JLabel.CENTER);
        tileLabel.setHorizontalAlignment(JLabel.CENTER);
        tokenLabel.setVerticalAlignment(JLabel.CENTER);
        tokenLabel.setHorizontalAlignment(JLabel.CENTER);

        if(h!=null){
            tileLabel.setIcon(new ImageIcon(h.getImage().getScaledInstance((int)(83*0.8), (int)(100*0.8),
            Image.SCALE_SMOOTH)));
        }else{
            tileLabel.setIcon(null);
            tileLabel.setBackground(Color.darkGray);
        }

        if(t!=null){
            tokenLabel.setIcon(new ImageIcon(t.getImage().getScaledInstance(80, 80,
            Image.SCALE_SMOOTH)));
        }else{
            tokenLabel.setIcon(null);
            tokenLabel.setBackground(Color.darkGray);
        }
        panel.repaint();
    }

    public void push(Player p){
        currentPlayer = p;
        locationPreferences.put(p, this.getLocation());
        repaint();
    }

    public static void push(Component c){
        centerOn = c;
    }

    public void pullLocation(Player p){
        currentPlayer = p;
        try {
            this.setLocation(locationPreferences.get(p));
        } catch (Exception e) {
            
        }
    }

    @Override
    public void setVisible(boolean b){
        if(b==true){
            Boolean setVis = true;
            try {
                if(!visible.get(currentPlayer)){
                    setVis = false;
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            super.setVisible(setVis);
        }else{
            super.setVisible(b);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==hide){
            this.dispose();
        }else if(e.getSource()==hidePermanent){
            visible.put(currentPlayer, false);
            this.dispose();
        }
    }
}
