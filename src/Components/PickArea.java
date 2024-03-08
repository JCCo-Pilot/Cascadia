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
import java.io.File;

import MathHelper.*;
import static java.lang.System.*;
public class PickArea extends JComponent implements MouseListener, ActionListener,AllowPickEventListener{

    private ArrayList<WildlifeTokens>tokens = new ArrayList<>();
    private int limitedSelection = -1;
    private int xSize,ySize;
    private int xPos,yPos;
    private boolean allowPick;
    private PickListener listener;
    private BufferedImage natureToken;
    private ArrayList<Player>players = new ArrayList<>();
    private JButton overpopButton = new JButton("Over-Population");
    private JButton clearToken = new JButton("ClearTokens");
    private HabitatTiles[]hexagons = new HabitatTiles[4];
    private ArrayList<HabitatTiles>ht = new ArrayList<>();
    public PickArea(int i,int x, int y , int xS, int yS){
        super();

        ht = new TileCreator().getTiles();

        construct(x,y,xS,yS);
        this.setVisible(true);
        createTokens();
        //sumChecker();
        randShuffle();
        while(isOverpopulated4()){
            randShuffle();
        }

        allowPick = true;

        clearToken.setBounds(27,745,300,50);
        clearToken.addActionListener(this);
        clearToken.setVisible(true);
        this.add(clearToken);

        overpopButton.setBounds(27,795,300,50);
        overpopButton.setVisible(isOverpopulated3());
        //overpopButton.setVisible(true);
        overpopButton.setActionCommand("Overpopulation");
        overpopButton.addActionListener(this);
        add(overpopButton);
        
        try{
            natureToken = ImageIO.read(new File("src/Entities/Tokens/NatureToken.png"));
        }catch(Exception e){
            out.println("Error in pulling pick area");
        }

        setVisible(true);
    }
    public void setPlayers(ArrayList<Player>play){
        players = play;
    }
    private void construct(int x, int y, int xS, int yS){
        enableInputMethods(true);
        addMouseListener(this);
        xPos = x; yPos = y;
        xSize = xS; ySize = yS;
        for (int i = 0;i<4;i++){
            //PointGenerator pg = new PointGenerator(56+69, 275+(146*i)-100, 70.0); //changed from y-6 to y-50
            hexagons[i]= ht.remove(0);
            hexagons[i].setX(56+69);
            hexagons[i].setY(275+(146*i)-100);
        }
    }
    public void paint(Graphics g){
        g.setColor(Color.RED);
        //g.fillRect(0, 0, xSize, ySize);
        //spacing+(size+space)*i
        for (int i = 0;i<4;i++){
            //g.fillRect(6+(106)*i, 6, 100, 100);
            hexagons[i].drawHexagon(g);
        }
        g.setColor(Color.BLACK);
        for (int i = 0;i<4;i++){
            g.drawImage(tokens.get(i).getImage(),131+69,250+(146*i)-100,70,70,null);
            //g.fillOval(131, 200+25+(106)*i, 50, 50);
        }
        g.setFont(new Font("Arial", 100, 50));
        if (players.size()>0){
            g.drawString(players.get(0).getName()+":",40,70);
        }
        g.drawImage(natureToken,50,680,50,50,null);
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
                
                Integer rand = (int) (Math.random()*(tokens.size()));
                tokens.add(firstFour.put(i, tokens.get(rand)));
            }
        }
        for(int i = 3; i>=0; i--){
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

    public WildlifeTokens removeAndReplaceToken(Integer index){
        if(index<=3&&index>=0){
            int rand = (int) (Math.random()*tokens.size());
            return tokens.set(index, tokens.remove(rand));
        }
        return null;
    }

    

    public Dimension getPreferredSize() {return new Dimension(xSize, ySize);}
    public Dimension getMinimumSize() {return new Dimension(xSize, ySize );}
    public Dimension getMaximumSize() {return new Dimension(xSize , ySize );}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        //131+69,250+(146*i)-100,70,70,
        if (limitedSelection==-1){
            for (int i = 0;i<4;i++){
                //pick stuff
                /*if (pointIsInside(200, 250+(146*i)-100, 70, 70, e)){
                    if (allowPick){
                        PickEvent event = new PickEvent(this, removeAndReplaceToken(i));
                        //need to delay the swap
                        //PickEvent event = new PickEvent(this, tokens.remove(i));
                        //tokens.set(i,null);
                        listener.process(event);
                        allowPick=false;
                        limitedSelection = i;
                        break;
                    }
                }*/
                //end of picking stuff
                if (hexagons[i].isPointInsideHexagon(e)){
                    HabitatTiles temp = hexagons[i];
                    out.println("Clicked "+i);
                    hexagons[i] =ht.remove(0); 
                    PickEvent pe = new PickEvent(this, temp);
                    listener.process(pe);
                    limitedSelection = i;
                }
            }
        }else if (limitedSelection>-1&&limitedSelection<4){
            //if you pick the habitat tile first
            if (hexagons[limitedSelection].getXPos()==0&&hexagons[limitedSelection].getYPos()==0){
                if(pointIsInside(200, 250+(146*limitedSelection)-100, 70, 70, e)&&allowPick){
                    PickEvent evnet = new PickEvent(e, removeAndReplaceToken(limitedSelection));
                    listener.process(evnet);
                    hexagons[limitedSelection].setX(56+69);
                    hexagons[limitedSelection].setY(175+(146*limitedSelection));
                    limitedSelection = -1;
                    //PickEvent ev = new PickEvent(this, true);
                    //listener.process(ev);
                    //players.add(players.remove(0));
                }
            }
            //if you pick the wildlife token first
            /*else if (!allowPick){
                if (hexagons[limitedSelection].isPointInsideHexagon(e)){
                    HabitatTiles temp = hexagons[limitedSelection];
                    hexagons[limitedSelection] =ht.remove(0); 
                    PickEvent pe = new PickEvent(this, temp);
                    listener.process(pe);
                    hexagons[limitedSelection].setX(56+69);
                    hexagons[limitedSelection].setY(175+(146*limitedSelection));
                    limitedSelection = -1;
                    //PickEvent ev = new PickEvent(this, true);
                    //listener.process(ev);
                    //players.add(players.remove(0));
                }
            }*/
            //hexagons[i].setX(56+69);
            //hexagons[i].setY(275+(146*i)-100);
            
        }
        if (isOverpopulated3()){
            overpopButton.setVisible(true);
        }else if (!isOverpopulated3()){
            overpopButton.setVisible(false);
        }
        repaint();
    }
    public void process(AllowPickEvent e){
        if (e.allowed()){
            allowPick= true;
            //players.add(players.remove(0));
            repaint();
        }
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void addListener(PickListener pl){listener = pl;}
    private boolean pointIsInside(int x, int y, int xSize, int ySize,MouseEvent e){
        if (e.getX()>x&&e.getX()<x+xSize){
            if (e.getY()>y&&e.getY()<y+ySize){
                return true;
            }
        }
        return false;
    }
    public int getXPos(){return xPos;}
    public int getYPos(){return yPos;}
    public int getXSize(){return xSize;}
    public int getYSize(){return ySize;}
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Overpopulation".equals(e.getActionCommand())){
            removeOverpopulation();
            ((JComponent) e.getSource()).setVisible(false);
        }else if (e.getSource()==clearToken){
            allowPick=true;
            limitedSelection =-1;
            players.add(players.remove(0));
            PickEvent ee = new PickEvent(this, "PickArea");
            listener.process(ee);
            //hexagons[limitedSelection].setX(56+69);
            //hexagons[limitedSelection].setY(175+(146*limitedSelection));
        }
        repaint();
    }
}
