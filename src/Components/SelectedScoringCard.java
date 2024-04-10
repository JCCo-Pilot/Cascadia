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
import Entities.WildlifeScoringCards.*;
import static java.lang.System.*;
public class SelectedScoringCard extends JFrame{
    private JPanel panel;
    private JLabel label;
    public SelectedScoringCard(){
        super("Scoring Card Pop Up");
        this.setSize(500,500);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new GridLayout(1,1,0,0));

        label = new JLabel();
        label.setBackground(Color.WHITE);
        label.setOpaque(true);
        label.setVisible(true);

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        this.add(panel);
        panel.add(label);
    }

    public void addScoringCard(BearCard bc){
        processImage(bc.getImage());
    }
    public void addScoringCard(ElkCard ec){
        processImage(ec.getImage());
    }
    public void addScoringCard(FoxCard fc){
        processImage(fc.getImage());
    }
    public void addScoringCard(HawkCard hc){
        processImage(hc.getImage());
    }
    public void addScoringCard(SalmonCard sc){
        processImage(sc.getImage());
    }

    public void processImage(BufferedImage ig){
        label.setIcon(new ImageIcon(ig.getScaledInstance(500,500,Image.SCALE_SMOOTH)));
    }
}
