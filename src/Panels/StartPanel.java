package Panels;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
public class StartPanel extends JPanel implements MouseListener,ActionListener{
    private GameListener listener;
    private JButton start;
    private JButton instructions;
    public StartPanel(){
        setLayout(null);
        start = new JButton("Start");
        start.addActionListener(this);
        start.setBounds(0, 0, 200, 50);
        instructions = new JButton("Instructions");
        instructions.setBounds(500,0,200,50);
        instructions.addActionListener(this);
        instructions.setVisible(true);
        instructions.setFocusable(false);
        start.setVisible(true);
        start.setFocusable(false);
        this.add(start);
        this.add(instructions);
        this.setVisible(true);
    }
    public void paint(Graphics g){
        paintComponents(g);
        //g.setColor(Color.BLACK);
        //g.fillRect(0, 0, 100, 100);
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==start){
            GameStateEvent gse = new GameStateEvent(this, 1);
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void setListener(GameListener gl){
        listener = gl;
    }
}
