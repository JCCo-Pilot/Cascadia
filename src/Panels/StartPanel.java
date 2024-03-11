package Panels;
import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import MathHelper.PointGenerator;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;

import static java.lang.System.*;
public class StartPanel extends JPanel implements MouseListener,ActionListener{
    private GameListener listener;
    private BufferedImage bg,starter,difficult;
    private JButton start;
    private JButton instructions;
    private int state = -1;
    private final int width = 1600;
    private final int height = 900;
    private Character difficulty;
    private ArrayList<BufferedImage>images = new ArrayList<>();
    private ArrayList<PointGenerator>hexagons = new ArrayList<>();
    private ArrayList<PointGenerator>playerOptions = new ArrayList<>();
    private ArrayList<PointGenerator>difficultyOptions = new ArrayList<>();
    public StartPanel(){
        //setSize(1600,900);
        setLayout(null);
        /*start = new JButton("Start");
        start.addActionListener(this);
        start.setBounds(696, 550, 175, 50);
        instructions = new JButton("Instructions");
        instructions.setBounds(1305,700,175,50);
        instructions.addActionListener(this);
        instructions.setVisible(true);
        instructions.setFocusable(false);
        start.setVisible(true);
        start.setFocusable(false);
        this.add(start);
        this.add(instructions);*/
        repaint();
        addMouseListener(this);
        makeHexagons();
        pullImages();
        this.setVisible(true);
    }
    private void makeHexagons(){
        int size = 100;
        Double inc = size*Math.sqrt(3)/2;
        int xInc = (int)(Math.round(inc));
        for (int i=0;i<3;i++){
            playerOptions.add(new PointGenerator(530+(265*i), 400+180, 150.0));
        }
        for (int i =0;i<5;i++){
            difficultyOptions.add(new PointGenerator(530-265+(265*i), 580, 150.0));
        }
        hexagons.add(new PointGenerator(795, 575, 150.0));
        hexagons.add(new PointGenerator(1460, 800, 150.0));
    }
    private void pullImages(){
        try{
            bg = ImageIO.read(new File("src/Panels/Background/StartBG.png"));
            starter = ImageIO.read(new File("src/Panels/Background/PlayerSelection.png"));
            difficult = ImageIO.read(new File("src/Panels/Background/DiffcultySelection.png"));
        }catch(Exception e){
            out.println("Errors in pulling instruction images");
        }
    }
    public void paint(Graphics g){
        super.paintComponents(g);
        super.paint(g);
        setBackground(Color.WHITE);
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
                g.drawImage(starter, 0, 0, 1590, 865, null);
                /*for(int i=0;i<playerOptions.size();i++){
                    playerOptions.get(i).drawHexagon(g);
                }*/
                
                /*g.setFont(new Font("Arial", 100, 70));
                g.drawString("Number of Players:",520,250);
                g.setFont(new Font("Arial", 100, 100));
                g.drawString("2", 570, 330+150);
                g.drawString("3", 770, 330+150);
                g.drawString("4", 970, 330+150);*/
            break;
            case 100:
                g.drawImage(difficult, 0, 0, 1590, 865, null);
                /*for (int i =0;i<difficultyOptions.size();i++){
                    difficultyOptions.get(i).drawHexagon(g);
                }*/
            break;
        }
        if (state==-1){
            g.setFont(new Font("Arial", 100, 100));
            g.drawString("Cascadia", 550, 200);
            /*g.setFont(new Font("Arial", 100, 40));
            g.drawString("Start",740,610);*/
            g.drawImage(bg,0,0,1590,865,null);
            //paintHexagons(g);
            paintComponents(g);
        }
        
    }
    private void paintHexagons(Graphics g){
        g.setColor(Color.GREEN);
        for(int i=0;i<hexagons.size();i++){
            hexagons.get(i).drawHexagon(g);
        }
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource()==start){
            //GameStateEvent gse = new GameStateEvent(this, 1);
            //listener.process(gse);
            state = 10;
            instructions.setVisible(false);
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
        if (state==-1){
            if (hexagons.get(0).isPointInsideHexagon(e)){
                state =100;
                hexagons.clear();
                repaint();
            }else if (hexagons.get(1).isPointInsideHexagon(e)){
                state =1;
                repaint();
            }
        }else if (state==100){
            for(int i =0;i<difficultyOptions.size();i++){
                if (difficultyOptions.get(i).isPointInsideHexagon(e)){
                    translate(i);
                    state =10;
                    repaint();
                }
            }
        }else if (state==10){
            if(playerOptions.get(0).isPointInsideHexagon(e)){
                GameStateEvent gse = new GameStateEvent(this, 1,difficulty);
                listener.process(gse);
            }else if(playerOptions.get(1).isPointInsideHexagon(e)){
                GameStateEvent gse = new GameStateEvent(this, 2,difficulty);
                listener.process(gse);
            }else if(playerOptions.get(2).isPointInsideHexagon(e)){
                GameStateEvent gse = new GameStateEvent(this, 3,difficulty);
                listener.process(gse);
            }
        }
    }
    public void translate(int i){
        switch (i) {
            case 0:
                difficulty = 'a';
            break;
            case 1:
                difficulty = 'b';
            break;
            case 2:
                difficulty = 'c';
            case 3:
                difficulty = 'd';
            break;
            case 4:
                difficulty = 'z';
            break;
        }
    }
    public void mouseReleased(MouseEvent e) {}    
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void setListener(GameListener gl){
        listener = gl;
    }
}
