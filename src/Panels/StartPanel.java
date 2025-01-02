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
    private UpdateEventListener uListener;
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

    private boolean difficultyLimit;
    public StartPanel(Boolean b){
        setLayout(null);
        repaint();
        addMouseListener(this);
        makeHexagons();
        pullImages();
        difficultyLimit = b;
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
    }
    private void pullImages(){
        try{
            bg = ImageIO.read(StartPanel.class.getResource("/Panels/Background/StartBG.png"));
            starter = ImageIO.read(StartPanel.class.getResource("/Panels/Background/PlayerSelection.png"));
            difficult = ImageIO.read(StartPanel.class.getResource("/Panels/Background/DiffcultySelection.png"));
        }catch(Exception e){
            out.println("Errors in pulling instruction images");
        }
    }
    public void paint(Graphics g){
        super.paintComponents(g);
        super.paint(g);
        setBackground(Color.WHITE);
        switch (state) {
            case 10:
                g.drawImage(starter, 0, 0, 1590, 865, null);
            break;
            case 100:
                if (difficultyLimit){
                    state = 10;
                    difficulty = 'a';
                    repaint();
                }else if (!difficultyLimit){
                    g.drawImage(difficult, 0, 0, 1590, 865, null);
                }
            break;
        }
        if (state==-1){
            g.setFont(new Font("Arial", 100, 100));
            g.drawString("Cascadia", 550, 200);
            g.drawImage(bg,0,0,1590,865,null);
            paintComponents(g);
        }
        
    }
    private void paintHexagons(Graphics g){
        g.setColor(Color.GREEN);
        for (PointGenerator hexagon : hexagons) {
            hexagon.drawHexagon(g);
        }
    }
    public void actionPerformed(ActionEvent e){
        if (uListener!=null){
            uListener.update(new UpdateEvent(this,e));
        }
        if (e.getSource()==start){
            state = 10;
            instructions.setVisible(false);
            start.setVisible(false);
        }else if (e.getSource()==instructions){
            state = 1;
            start.setVisible(false);
        }
        repaint();
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        if (uListener!=null){
            uListener.update(new UpdateEvent(this, e));
        }
        if (state==-1){
            if (hexagons.get(0).isPointInsideHexagon(e)){
                state =100;
                hexagons.clear();
                repaint();
            }
        }else if (state==100){
            if (!difficultyLimit){
                for(int i =0;i<difficultyOptions.size();i++){
                    if (difficultyOptions.get(i).isPointInsideHexagon(e)){
                        translate(i);
                        state =10;
                        repaint();
                    }
                }
            }else if (difficultyLimit){
                state = 10;
                
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
            break;
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
    public void setUListener(UpdateEventListener uel){
        uListener = uel;
    }
}
