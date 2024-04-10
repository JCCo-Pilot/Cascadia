package Components;
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
public class Selected extends JFrame implements ActionListener{
    private JPanel panel;
    private JPanel buttonPanel;
    private JLabel tileLabel;
    private JLabel tokenLabel;
    private static Component centerOn;
    private static Boolean movePopUp = false;
    private static HashMap<Player, Point> locationPreferences = new HashMap<Player, Point>();
    public Selected(){
        super("Player Selection");
        this.setSize(500, 250);
        this.setLocationRelativeTo(centerOn);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLayout(new BorderLayout());
        panel = new JPanel();
        //this.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.setLayout(new GridLayout(1, 2, 10, 20));
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
        this.add(panel);
        panel.add(tileLabel);
        panel.add(tokenLabel);
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(centerOn);
        if(!movePopUp){
            frame.setVisible(true);
            Integer i = JOptionPane.showConfirmDialog(frame, "Players, you can move around the Player Selection Panel to your liking \nand it will reappear where you last left it on your turn.", "Player Selection Panel Information", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            movePopUp = true;
        }
        //this.setVisible(true);
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
            tileLabel.setIcon(new ImageIcon(h.getImage().getScaledInstance(173, 200,
            Image.SCALE_SMOOTH)));
        }else{
            tileLabel.setIcon(null);
            tileLabel.setBackground(Color.darkGray);
        }

        if(t!=null){
            tokenLabel.setIcon(new ImageIcon(t.getImage().getScaledInstance(200, 200,
            Image.SCALE_SMOOTH)));
        }else{
            tokenLabel.setIcon(null);
            tokenLabel.setBackground(Color.darkGray);
        }
    }

    public void push(Player p){
        locationPreferences.put(p, this.getLocation());
    }

    public static void push(Component c){
        centerOn = c;
    }

    public void pullLocation(Player p){
        try {
            this.setLocation(locationPreferences.get(p));
        } catch (Exception e) {
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
