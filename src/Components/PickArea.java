package Components;
import Entities.*;
import Entities.Enums.CardAnimals;

import java.util.*;
import javax.swing.*;
import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.print.DocFlavor.INPUT_STREAM;

import java.awt.image.*;
import MathHelper.*;
import static java.lang.System.*;
public class PickArea extends JComponent implements MouseListener, ActionListener{

    private ArrayList<WildlifeTokens>tokens = new ArrayList<>();
    private int numPlayers;
    private int xSize,ySize;
    private int xPos,yPos;
    private JButton overpopButton = new JButton("Over-Population");
    private PointGenerator[]hexagons = new PointGenerator[4];
    public PickArea(int i,int x, int y , int xS, int yS){
        super();
        construct(x,y,xS,yS);
        numPlayers = i;
        this.setVisible(true);
        createTokens();
        //sumChecker();
        randShuffle();
        while(isOverpopulated4()){
            randShuffle();
        }
        createHabitatTiles();

        overpopButton.setBounds(0,800,150,50);
        overpopButton.setVisible(isOverpopulated3());
        overpopButton.setActionCommand("Overpopulation");
        overpopButton.addActionListener(this);
        add(overpopButton);
        
        setVisible(true);
    }
    private void construct(int x, int y, int xS, int yS){
        enableInputMethods(true);
        addMouseListener(this);
        xPos = x; yPos = y;
        xSize = xS; ySize = yS;
        for (int i = 0;i<4;i++){
            PointGenerator pg = new PointGenerator(56, 250+(106*i), 50.0); //changed from y-6 to y-50
            hexagons[i]= pg;
        }
    }
    public void paint(Graphics g){
        g.setColor(Color.RED);
        //spacing+(size+space)*i
        for (int i = 0;i<4;i++){
            //g.fillRect(6+(106)*i, 6, 100, 100);
            hexagons[i].drawHexagon(g);
        }
        g.setColor(Color.BLUE);
        for (int i = 0;i<4;i++){
            g.drawImage(tokens.get(i).getImage(),131,200+25+(106*i),50,50,null);
            //g.fillOval(131, 200+25+(106)*i, 50, 50);
        }
        g.setFont(new Font("Arial", 100, 50));
        g.drawString("Player 1:",10,70);
        paintComponents(g);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D antiAlias = (Graphics2D) g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paint(g);
    }
    private void createTokens(){
        for (int i =0;i<20;i++){
            tokens.add(new WildlifeTokens(CardAnimals.BEAR));
            tokens.add(new WildlifeTokens(CardAnimals.ELK));
            tokens.add(new WildlifeTokens(CardAnimals.SALMON));
            tokens.add(new WildlifeTokens(CardAnimals.HAWK));
            tokens.add(new WildlifeTokens(CardAnimals.FOX));
        }
    }
    private void createHabitatTiles(){
        //create all 85 tiles
        switch(numPlayers){
            case 2:
                //remove 42
            break;
            case 3:
                //remove 22
            break;
            case 4:
                //remove 2 
            break;
        }
    }
    private void randShuffle(){
        int numTime = (int)(Math.random()*90)+10;
        for (int i = 0;i<numTime;i++){
            Collections.shuffle(tokens);
        }
    }
    private void sumChecker(){
        int numBear = 0;
        int numFox = 0;
        int numElk = 0;
        int numSalmon = 0;
        int numHawk =0;
        for (int i = 0; i<tokens.size();i++){
            if (tokens.get(i).getName().equals("Bear")){
                numBear++;
            }else if (tokens.get(i).getName().equals("Elk")){
                numElk++;
            }else if (tokens.get(i).getName().equals("Salmon")){
                numSalmon++;
            }else if (tokens.get(i).getName().equals("Fox")){
                numFox++;
            }else if (tokens.get(i).getName().equals("Hawk")){
                numHawk++;
            }
        }
        out.println("Bears -"+numBear);
        out.println("Elks -"+numElk);
        out.println("Salmon -"+numSalmon);
        out.println("Fox -"+numFox);
        out.println("Hawk -"+numHawk);
    }

    private void removeOverpopulation(){
        if(!isOverpopulated3()){
            return;
        }
        CardAnimals max = getHighestShownTokenType();
        HashMap<Integer, WildlifeTokens> firstFour = new HashMap<Integer, WildlifeTokens>();//so we can keep original index of ones that arent removed
        for(int i = 0; i<4; i++){
            firstFour.put(i, tokens.remove(0));
        }
        for(Integer i:firstFour.keySet()){
            if(firstFour.get(i).getType()==max){
                Integer rand = (int) (Math.random()*tokens.size());
                tokens.add(firstFour.put(i, tokens.get(rand)));
            }
        }
        for(int i = 3; i<=0; i--){
            tokens.add(0, firstFour.get(i));
        }
        
    }

    private Boolean isOverpopulated3(){
        HashMap<CardAnimals, Integer> histogram = new HashMap<CardAnimals, Integer>();
        for(int i = 0; i<4; i++){
            WildlifeTokens w = tokens.get(i);
            if(histogram.containsKey(w.getType())){
                histogram.put(w.getType(), histogram.get(w.getType())+1);
            }else{
                histogram.put(w.getType(), 1);
            }
        }
        //find max of the values
        Integer max = Integer.MIN_VALUE;
        for(CardAnimals c:histogram.keySet()){
            if(histogram.get(c)>max){
                max = histogram.get(c);
            }
        }
        if(max>=3){
            return true;
        }
        return false;
    }

    private CardAnimals getHighestShownTokenType(){
        HashMap<CardAnimals, Integer> histogram = new HashMap<CardAnimals, Integer>();
        for(int i = 0; i<4; i++){
            WildlifeTokens w = tokens.get(i);
            if(histogram.containsKey(w.getType())){
                histogram.put(w.getType(), histogram.get(w.getType())+1);
            }else{
                histogram.put(w.getType(), 1);
            }
        }
        //find max of the values
        Integer max = Integer.MIN_VALUE;
        CardAnimals animal = CardAnimals.BEAR;//default
        for(CardAnimals c:histogram.keySet()){
            if(histogram.get(c)>max){
                max = histogram.get(c);
                animal = c;
            }
        }
        return animal;
    }

    private Boolean isOverpopulated4(){
        HashMap<CardAnimals, Integer> histogram = new HashMap<CardAnimals, Integer>();
        for(int i = 0; i<4; i++){
            WildlifeTokens w = tokens.get(i);
            if(histogram.containsKey(w.getType())){
                histogram.put(w.getType(), histogram.get(w.getType())+1);
            }else{
                histogram.put(w.getType(), 1);
            }
        }
        //find max of the values
        Integer max = Integer.MIN_VALUE;
        for(CardAnimals c:histogram.keySet()){
            if(histogram.get(c)>max){
                max = histogram.get(c);
            }
        }
        if(max>=4){
            return true;
        }
        return false;
    }

    

    public Dimension getPreferredSize() {return new Dimension(xSize, ySize);}
    public Dimension getMinimumSize() {return new Dimension(xSize, ySize );}
    public Dimension getMaximumSize() {return new Dimension(xSize , ySize );}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        for (int i = 0;i<4;i++){
            
        }
        repaint();
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public int getXPos(){return xPos;}
    public int getYPos(){return yPos;}
    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Overpopulation".equals(e.getActionCommand())){
            removeOverpopulation();
            ((JComponent) e.getSource()).setVisible(false);
        }
        repaint();
    }
}
