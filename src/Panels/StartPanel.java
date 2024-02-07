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
        start = new JButton("Start");
        start.addActionListener(this);
        instructions = new JButton("Instructions");
        instructions.addActionListener(this);
       this.addMouseListener(this); 
    }
    public void paint(Graphics g){

    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==start){
            GameStateEvent gse = new GameStateEvent(this, 1);
        }
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void setListener(GameListener gl){
        listener = gl;
    }
}
