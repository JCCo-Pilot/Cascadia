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
public class Selected extends JFrame{
    private JPanel panel;
    private JLabel tileLabel;
    private JLabel tokenLabel;
    public Selected(){
        super("Player Selection");
        this.setSize(500, 250);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
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
}
