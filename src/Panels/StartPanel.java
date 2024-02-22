package Panels;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import MathHelper.PointGenerator;

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
    private ArrayList<PointGenerator>hexagons = new ArrayList<>();
    private ArrayList<PointGenerator>playerOptions = new ArrayList<>();
    public StartPanel(){
        //setSize(1600,900);
        setLayout(null);
        start = new JButton("Start");
        start.addActionListener(this);
        start.setBounds(695, 580, 175, 50);
        /*instructions = new JButton("Instructions");
        instructions.setBounds(700,360,200,50);
        instructions.addActionListener(this);
        instructions.setVisible(true);
        instructions.setFocusable(false);*/
        start.setVisible(true);
        start.setFocusable(false);
        this.add(start);
        //this.add(instructions);*/
        repaint();
        makeHexagons();
        this.setVisible(true);
    }
    private void makeHexagons(){
        int size = 100;
        Double inc = size*Math.sqrt(3)/2;
        int xInc = (int)(Math.round(inc));
        for (int i=0;i<3;i++){
            playerOptions.add(new PointGenerator(600+(200*i), 300, 100.0));
        }
        for (int i =0; i<10;i++){
            //hexagons.add(new PointGenerator(xInc*2*(i+1), 0+150, 100.0));
            hexagons.add(new PointGenerator(xInc*2*(i), 150*2+150, 100.0));
            hexagons.add(new PointGenerator(xInc*2*(i), 150*4+150, 100.0));
        }
        for (int i =0; i<10;i++){
            //hexagons.add(new PointGenerator(xInc+(174*i), 0, 100.0));
            //hexagons.add(new PointGenerator(xInc+(174*i), 150+150, 100.0));
            hexagons.add(new PointGenerator(xInc+(174*i), 150*3+150, 100.0));
            hexagons.add(new PointGenerator(xInc+(174*i), 150*5+150, 100.0));
        }
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
                g.drawImage(images.get(9), 0, 0, width, height, null);
                g.drawImage(images.get(10), width/2, 0, width, height, null);
            break;
            case 7:
                g.drawImage(images.get(11), 0, 0, width, height, null);
                g.drawImage(images.get(12), width/2, 0, width, height, null);
            break;
            case 8:
                g.drawImage(images.get(13), 0, 0, width, height, null);
                g.drawImage(images.get(14), width/2, 0, width, height, null);
            break;
            case 9:
                g.drawImage(images.get(14), 0, 0, width, height, null);
                g.drawImage(images.get(15), width/2, 0, width, height, null);
            break;
            case 10:
                for(int i=0;i<playerOptions.size();i++){
                    playerOptions.get(i).drawHexagon(g);
                }
            break;
        }
        if (state==-1){
            g.setFont(new Font("Arial", 100, 100));
            g.drawString("Cascadia", 550, 200);
            paintHexagons(g);
        }
        
    }
    private void paintHexagons(Graphics g){
        for(int i=0;i<hexagons.size();i++){
            hexagons.get(i).drawHexagon(g);
        }
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==start){
            //GameStateEvent gse = new GameStateEvent(this, 1);
            //listener.process(gse);
            state = 10;
            //instructions.setVisible(false);
            start.setVisible(false);
        }else if (e.getSource()==instructions){
            state = 1;
            //instructions.setVisible(false);
            start.setVisible(false);
        }
        repaint();
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
