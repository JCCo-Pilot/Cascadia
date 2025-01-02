package Components;
import Entities.*;
import Entities.Enums.CardAnimals;

import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import EventAndListener.*;
import java.awt.*;
import java.awt.event.*;

import Panels.ScoreTesterPanel;

import static java.lang.System.*;
public class PickArea extends JComponent implements MouseListener, ActionListener,AllowPickEventListener{

    private ArrayList<WildlifeTokens>tokens = new ArrayList<>();
    private int limitedSelection = -1;
    private int xSize,ySize;
    private int xPos,yPos;
    private boolean allowPick;

    private EndGameListener egl;

    private Boolean tokenTaken = false;
    private Boolean tileTaken = false;

    private Boolean tokenPlaced = false;
    private Boolean tilePlaced = false;

    private boolean removeTrigger;
    private ArrayList<Integer>removal = new ArrayList<>();

    private boolean stopDoublePick;

    private boolean pickCombo;
    private int pickedHex;

    private HashSet<Selected> selectionPanels = new HashSet<Selected>();

    private final String[] choices = {"Choose an option:","Any Combination of Tiles+Tokens","Select Tokens To Remove"};
    
    private PickListener listener;
    private boolean socialSigma;
    private ArrayList<Player>players = new ArrayList<>();
    private Boolean overPopUsed = false;
   
    private JButton overpopButton = constructButton("Over-Population");
    private JButton clearToken = constructButton("End Turn Without Placing Token");

    private JComboBox<String>jcb;
    private JButton spendToken = constructButton("Spend");
    private JButton confirmButton = constructButton("Confirm Token Removal");

    private HabitatTiles[]hexagons = new HabitatTiles[4];
    private ArrayList<HabitatTiles>ht = new ArrayList<>();

    private UpdateEventListener uListener;
    public PickArea(int i,int x, int y , int xS, int yS){
        super();
        ht = new TileCreator().getTiles();

        construct(x,y,xS,yS);
        this.setVisible(true);
        
        createTokens();
        randShuffle();
        while(isOverpopulated4()){
            randShuffle();
        }

        allowPick = true;

        clearToken.setBounds(33,745,270,30);
        clearToken.addActionListener(this);
        clearToken.setVisible(false);
        this.add(clearToken);
        //795
        spendToken.setBounds(33,775,70,30);
        spendToken.addActionListener(this);
        spendToken.setVisible(false);
        add(spendToken);

        jcb = new JComboBox<>(choices);
        jcb.setBounds(103,775,200,20);
        jcb.addActionListener(this);
        jcb.setVisible(false);
        add(jcb);

        
        confirmButton.setBounds(33,745,270,30);
        confirmButton.addActionListener(this);
        confirmButton.setVisible(false);
        add(confirmButton);

        overpopButton.setBounds(33,745,270,30);
        overpopButton.setVisible(isOverpopulated3());
        overpopButton.setActionCommand("Overpopulation");
        overpopButton.addActionListener(this);
        add(overpopButton);
        

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
            hexagons[i]= ht.remove(0);
            hexagons[i].setX(56+69);
            hexagons[i].setY(275+(146*i)-100);
        }
    }
    public void paint(Graphics g){
        periodic();
        g.setColor(Color.RED);
        for (int i = 0;i<4;i++){
            hexagons[i].drawHexagon(g);
        }
        g.setColor(Color.BLACK);
        for (int i = 0;i<4;i++){
            g.drawImage(tokens.get(i).getImage(),131+69,250+(146*i)-100,70,70,null);
        }
        //options
        g.setFont(new Font("Arial", 100, 50));
        if (players.size()>0){
            g.drawString(players.get(0).getName()+":",40,70);
        }
        g.setFont(new Font("Arial", 100, 15));
        g.drawString("Tokens: "+tokens.size(),200,140);
        //other more different option
        g.setFont(new Font("Arial",100,30));
        g.drawString("Nature Tokens: "+players.get(0).getNatureTokens(),50,720);
        paintComponents(g);

        for (int i =0;i<4&&i<removal.size();i++){
            g.drawRect(200,(250+(146*removal.get(i)))-100,70,70);
        }
        uListener.update(new UpdateEvent(this, players));
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        periodic();
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
                WildlifeTokens w = tokens.get(rand);
                tokens.remove(w);
                tokens.add(firstFour.put(i, w));
                
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
        if (index<4&&index>-1){
            WildlifeTokens temp = tokens.get(index);
            tokens.set(index,tokens.remove(4));
            return temp;

        }
        return null;
    }

    

    public Dimension getPreferredSize() {return new Dimension(xSize, ySize);}
    public Dimension getMinimumSize() {return new Dimension(xSize, ySize );}
    public Dimension getMaximumSize() {return new Dimension(xSize , ySize );}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        if (pickCombo&&pickedHex!=-1&&!stopDoublePick){
            for (int i = 0;i<4;i++){
                if (hexagons[i].isPointInsideHexagon(e)){
                    HabitatTiles temp = hexagons[i];
                    //out.println("Clicked "+i);
                    hexagons[i] =ht.remove(0); 
                    PickEvent pe = new PickEvent(this, temp);
                    tileTaken = true;
                    if (listener!=null){
                        listener.process(pe);
                    }
                    pickedHex = i;
                    stopDoublePick = true;
                }
            }
        }
        if (pickCombo&&pickedHex>-1){
            out.println("Line 310");
            if (hexagons[pickedHex].getXPos()==0&&hexagons[pickedHex].getYPos()==0){
                out.println("Line 312");
                for (int i =0;i<4;i++){
                    if(pointIsInside(200, 250+(146*i)-100, 70, 70, e)){
                        PickEvent event = new PickEvent(e, removeAndReplaceToken(i));
                        tokenTaken = true;
                        if (listener!=null){
                            listener.process(event);
                        }
                        hexagons[pickedHex].setX(56+69);
                        hexagons[pickedHex].setY(175+(146*pickedHex));
                        pickedHex = -1;
                        pickCombo=false;
                    }
                }
            }
        }
        if (!pickCombo&&removeTrigger){
            for (int i =0;i<4;i++){
                if (pointIsInside(200, 250+(146*i)-100, 70, 70, e)){
                    removal.add(i);
                }
            }
        }
        if (!pickCombo&&limitedSelection==-1&&!removeTrigger&&!stopDoublePick){
            for (int i = 0;i<4;i++){
                if (hexagons[i].isPointInsideHexagon(e)){
                    HabitatTiles temp = hexagons[i];
                    //out.println("Clicked "+i);
                    hexagons[i] =ht.remove(0); 
                    PickEvent pe = new PickEvent(this, temp);
                    tileTaken = true;
                    
                    if (listener!=null){
                        listener.process(pe);
                    }
                    limitedSelection = i;
                    stopDoublePick = true;
                }
            }
        }else if (limitedSelection>-1&&limitedSelection<4){
            //if you pick the habitat tile first
            if (hexagons[limitedSelection].getXPos()==0&&hexagons[limitedSelection].getYPos()==0){
                if(pointIsInside(200, 250+(146*limitedSelection)-100, 70, 70, e)&&allowPick){
                    PickEvent evnet = new PickEvent(e, removeAndReplaceToken(limitedSelection));
                    tokenTaken = true;
                    if (listener!=null){
                        listener.process(evnet);
                    }
                    hexagons[limitedSelection].setX(56+69);
                    hexagons[limitedSelection].setY(175+(146*limitedSelection));
                    limitedSelection = -1;
                    stopDoublePick = true;
                }
            }
        }
        uListener.update(new UpdateEvent(this, players));
        periodic();
        repaint();
    }
    public void process(AllowPickEvent e){
        if (e.allowed()){
            allowPick= true;
            repaint();
        }else if (e.movedOn()!=null){
            stopDoublePick = false;
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
    public void endUpdate(){
        Thread.currentThread().setPriority(10);
        stopDoublePick = false;
        allowPick=true;
        limitedSelection =-1;
        pickedHex = 0;
        tokenTaken = false;
        tileTaken = false;
        tokenPlaced = false;
        tilePlaced = false;
        overPopUsed = false;
        clearToken.setVisible(false);
        if (isOverpopulated3()){
            overpopButton.setVisible(true);
        }else if (!isOverpopulated3()){
            overpopButton.setVisible(false); 
        }
        for(Selected s:selectionPanels){
            s.dispose();
            selectionPanels.remove(s);
        }
        ScoreTesterPanel.update();
        periodic();
        players.get(0).updateTypes();
        Thread.currentThread().setPriority(5);
    }
    public void placement(Boolean b){
        //true is token, false is tile
        if(b){
            tokenPlaced = true;
            spendToken.setVisible(false);
            jcb.setVisible(false);
        }else{
            tilePlaced = true;
        }
        repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        periodic();
        if ("Overpopulation".equals(e.getActionCommand())){
            removeOverpopulation();
            overPopUsed = true;
            ((JComponent) e.getSource()).setVisible(false);
        }else if (e.getSource()==clearToken){
            allowPick=true;
            limitedSelection =-1;
            pickedHex = -1;
            Player temp = players.remove(0);
            temp.decrement();
            players.add(temp);
            PickEvent ee = new PickEvent(this, "PickArea");
            
            listener.process(ee);
            ((PlayerDisplay)listener).setupNew();
            endUpdate();
        }else if(e.getSource()==spendToken&&jcb.getSelectedIndex()==1&&!pickCombo){//fixes the infinte spend abilities
            pickCombo = true;
            players.get(0).spendNatureToken();
            spendToken.setVisible(false);
            jcb.setVisible(false);
            out.println("438");
        }else if (e.getSource()==spendToken&&jcb.getSelectedIndex()==2&&!removeTrigger){
            spendToken.setVisible(false);
            jcb.setVisible(false);
            confirmButton.setVisible(true);
            removeTrigger = true;
            players.get(0).spendNatureToken();
            repaint();
            jcb = new JComboBox<>(choices);
            jcb.setBounds(103,775,200,20);
            jcb.addActionListener(this);
            jcb.setVisible(false);
            add(jcb);
        }else if (e.getSource()==confirmButton&&removeTrigger){
            ArrayList<WildlifeTokens>wt = new ArrayList<>();
            for (int i =0;i<4&&i<removal.size();i++){
                wt.add(removeAndReplaceToken(removal.get(i)));
            }
            removeTrigger=false;
            confirmButton.setVisible(false);   
            removal.clear();
            tokens.addAll(wt);
        }
        uListener.update(new UpdateEvent(this, players));
        repaint();
        periodic();
    }
    public void setReginaPerez(EndGameListener eg){
        egl = eg;
    }
    private static JButton constructButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.BLACK);
        Border line = new LineBorder(Color.GRAY);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        return button;
    }
    public void middleClick(MouseEvent e){
        if(tileTaken&&!tokenTaken){
            PickEvent event = new PickEvent(e, removeAndReplaceToken(limitedSelection));
            tokenTaken = true;
                    if (listener!=null){
                        listener.process(event);
                    }
                    hexagons[limitedSelection].setX(56+69);
                    hexagons[limitedSelection].setY(175+(146*limitedSelection));
                    limitedSelection = -1;
                    stopDoublePick = true;
        }else if(tokenTaken&&!tokenPlaced){
            allowPick=true;
            limitedSelection =-1;
            pickedHex = -1;
            Player temp = players.remove(0);
            temp.decrement();
            players.add(temp);
            PickEvent ee = new PickEvent(this, "PickArea");
            
            listener.process(ee);
            ((PlayerDisplay)listener).setupNew();
            endUpdate();
        }
        uListener.update(new UpdateEvent(this, players));
        periodic();
        repaint();
    }
    private void periodic(){
        if (players.get(0).getTurn()==0){
            EndGameEvent ege = new EndGameEvent(this, true);
            egl.endGameTime(ege);
        }
        if (players.get(0).getNatureTokens()>0&&!removeTrigger){
            spendToken.setVisible(true);
            jcb.setVisible(true);
        }
        if ((players.get(0).getNatureTokens()<=0&&!removeTrigger)){
            spendToken.setVisible(false);
            jcb.setVisible(false);
        }
        if(tilePlaced){
            spendToken.setVisible(false);
            jcb.setVisible(false);
        }
        if(tilePlaced&tokenTaken){
            clearToken.setVisible(true);
        }
        if(tileTaken){
            overpopButton.setVisible(false);
        }
        while(isOverpopulated4()){
            removeOverpopulation();
        }
        if(isOverpopulated3()&&!overPopUsed){
            overpopButton.setVisible(true);
        }
        ((PlayerDisplay)listener).currentPlayer().updateTypes();
        Selected.currentPlayer = ((PlayerDisplay)listener).currentPlayer();
        if(selectionPanels.size()==0&&!(((PlayerDisplay)listener).getCurrentTile() == null&&((PlayerDisplay)listener).getCurrentToken() == null)){
            Selected s = new Selected();
            s.push(((PlayerDisplay)listener).getCurrentTile(), ((PlayerDisplay)listener).getCurrentToken());
            selectionPanels.add(s);
            s.pullLocation(((PlayerDisplay)listener).currentPlayer());
            s.setVisible(true);
        }
        for(Selected s:selectionPanels){
            s.push(((PlayerDisplay)listener).getCurrentTile(), ((PlayerDisplay)listener).getCurrentToken());
            s.push(((PlayerDisplay)listener).currentPlayer());
        }        
    }
    public void setUListener(UpdateEventListener uel){
        uListener = uel;
    }
}