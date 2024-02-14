package Panels;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import static java.lang.System.*;
public class StartPanel extends JPanel implements MouseListener,ActionListener{
    private GameListener listener;
    private JButton start;
    private JButton instructions;
    private int state = -1;
    private final int width = 1600;
    private final int height = 900;
    private ArrayList<BufferedImage>images = new ArrayList<>();
    public StartPanel(){
        //setSize(1600,900);
        setLayout(null);
        start = new JButton("Start");
        start.addActionListener(this);
        start.setBounds(700, 300, 200, 50);
        instructions = new JButton("Instructions");
        instructions.setBounds(700,360,200,50);
        instructions.addActionListener(this);
        instructions.setVisible(true);
        instructions.setFocusable(false);
        start.setVisible(true);
        start.setFocusable(false);
        this.add(start);
        this.add(instructions);
        repaint();
        this.setVisible(true);
    }
    private void pullImages(){
        try{
            for (int i = 1;i<17;i++){
                BufferedImage temp = ImageIO.read(StartPanel.class.getResource("/Images/Instructions/"+i+".png"));
                images.add(temp);
            }
        }catch(Exception e){
            out.println("Errors in pulling instruction images");
        }
    }
    public void paint(Graphics g){
        super.paintComponents(g);
        super.paint(g);
        switch (state) {
            case 1:
                g.drawImage(images.get(0), 0, 0, width, height, null);
            break;
            case 2:
                g.drawImage(images.get(1), 0, 0, width, height, null);
                g.drawImage(images.get(2), width/2, 0, width, height, null);
            break;
            case 3:
                g.drawImage(images.get(3), 0, 0, width, height, null);
                g.drawImage(images.get(4), width/2, 0, width, height, null);
            break;
            case 4:
                g.drawImage(images.get(5), 0, 0, width, height, null);
                g.drawImage(images.get(6), width/2, 0, width, height, null);
            break;
            case 5:
                g.drawImage(images.get(7), 0, 0, width, height, null);
                g.drawImage(images.get(8), width/2, 0, width, height, null);
            break;
            case 6:
            break;
            case 7:
            break;
            case 8:
            break;
        }
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==start){
            GameStateEvent gse = new GameStateEvent(this, 1);
            listener.process(gse);
        }else if (e.getSource()==instructions){
            state = 1;
            instructions.setVisible(false);
            start.setVisible(false);
        }
        repaint();
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {}    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void setListener(GameListener gl){
        listener = gl;
    }
}
