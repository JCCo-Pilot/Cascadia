package Entities;
import java.awt.*;
import javax.swing.*;

import Entities.Enums.CardAnimals;
import Entities.Enums.Habitats;
import MathHelper.MathPoint;
import MathHelper.PointGenerator;
import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;


import java.awt.event.*;
import java.awt.geom.*;

import static java.lang.System.*;
public class HabitatTiles extends PointGenerator{
    public static Integer LEFT = 0;
    public static Integer DOWN_LEFT = 1;
    public static Integer DOWN_RIGHT = 2;
    public static Integer RIGHT = 3;
    public static Integer UP_RIGHT = 4;
    public static Integer UP_LEFT = 5;


    private BufferedImage image;
    private final ArrayList<CardAnimals> animals = new ArrayList<CardAnimals>();
    
    private HashMap<Integer, Habitats> habitatSides = new HashMap<Integer, Habitats>();
    private final HashMap<Integer, HabitatTiles> connections = new HashMap<Integer, HabitatTiles>();
    private Integer rotation = 0;
    private String imageName;
    private String name;
    public Boolean isKeystone;
    public Integer x, y;
    private static int emptyCnt;
    private static int normieCnt;

    public HashSet<String> highlights = new HashSet<String>();
    private static final HashMap<String, HabitatTiles> allTiles = new HashMap<String, HabitatTiles>();

    public HabitatTiles(double size){
        super(0, 0, (int)size);
        imageName = "empty"+ ++emptyCnt;
        allTiles.put(this.imageName, this);
    }

    public HabitatTiles(){
        super(0, 0, 0);
        imageName = "empty empty" + ++emptyCnt;
        allTiles.put(this.imageName, this);
    }

    public HabitatTiles(String imageName,int x, int y, Double size){
        super(x,y,size);
        try{
            image = ImageIO.read(Objects.requireNonNull(HabitatTiles.class.getResource("/Entities/StarterTilePics/" + imageName + ".png")));
        }catch(Exception _){
        }
        allTiles.put(this.imageName, this);
    }   

    public HabitatTiles(Integer x, Integer y, Double size){
        super(x, y, size);
        imageName = "N/A" + ++emptyCnt;
        allTiles.put(this.imageName, this);
    }

    public HabitatTiles(String imageName,boolean isKey,int x, int y, double sz){
        super(x,y,sz);
        this.imageName = imageName+ ++normieCnt;
        this.isKeystone = isKey;

        String env = imageName.substring(0, imageName.indexOf("-"));
        String ani = imageName.substring(imageName.indexOf("-")+1);

        ArrayList<String>environments = new ArrayList<>();
        ArrayList<String>animals = new ArrayList<>();

        if (env.contains("+")){
            environments.add(env.substring(0,env.indexOf("+")));
            env = env.substring(env.indexOf("+")+1);
        }
        environments.add(env);

        if(ani.indexOf("-")!=ani.lastIndexOf("-")){
            //3 habitats
        }
        allTiles.put(this.imageName, this);
    }

    public HabitatTiles(String imageName, String[] habitats, String[] animals, Boolean isKeyStone){
        super(0, 0, 0);
        this.imageName = imageName+ ++normieCnt;
        this.isKeystone = isKeyStone;

        //map each side to a habitat
        if(isKeystone){
            for(int i = 0; i<6; i++){
                habitatSides.put(i, Habitats.toHabitat(habitats[0]));
            }
        }else{
            habitatSides.put(5, Habitats.toHabitat(habitats[0]));
            habitatSides.put(0, Habitats.toHabitat(habitats[0]));
            habitatSides.put(1, Habitats.toHabitat(habitats[0]));
            habitatSides.put(2, Habitats.toHabitat(habitats[1]));
            habitatSides.put(3, Habitats.toHabitat(habitats[1]));
            habitatSides.put(4, Habitats.toHabitat(habitats[1]));
        }

        for(int i = 0; i<animals.length; i++){
            this.animals.add(CardAnimals.StringToAnimal(animals[i]));
        }

        try{
            image = ImageIO.read(Objects.requireNonNull(HabitatTiles.class.getResource("/Entities/StarterTilePics/" + imageName + ".png")));
        }catch(Exception _){
        }
        allTiles.put(this.imageName, this);
    }

    public HabitatTiles(String imageName, String[] habitats, String[] animals, boolean isKeyStone, Integer x, Integer y, Double size){
        super(x, y, size);
        this.imageName = imageName+ ++normieCnt;
        this.isKeystone = isKeyStone;

        //map each side to a habitat
        if(isKeystone){
            for(int i = 0; i<6; i++){
                habitatSides.put(i, Habitats.toHabitat(habitats[0]));
            }
        }else{
            habitatSides.put(5, Habitats.toHabitat(habitats[0]));
            habitatSides.put(0, Habitats.toHabitat(habitats[0]));
            habitatSides.put(1, Habitats.toHabitat(habitats[0]));
            habitatSides.put(2, Habitats.toHabitat(habitats[1]));
            habitatSides.put(3, Habitats.toHabitat(habitats[1]));
            habitatSides.put(4, Habitats.toHabitat(habitats[1]));
        }

        for(int i = 0; i<animals.length; i++){
            this.animals.add(CardAnimals.StringToAnimal(animals[i]));
        }

        try{
            image = ImageIO.read(Objects.requireNonNull(HabitatTiles.class.getResource("/Entities/Images/" + imageName + ".png")));
        }catch(Exception _){
        }
        allTiles.put(this.imageName, this);
    }

    public HabitatTiles(String imageName, String[] habitats, String[] animals, boolean isKeyStone, Integer x, Integer y, Double size,boolean isStarter){
        super(x, y, size);
        PrintTester.print("Called");
        this.imageName = imageName+ ++normieCnt;
        this.isKeystone = isKeyStone;

        //map each side to a habitat
        if(isKeystone){
            for(int i = 0; i<6; i++){
                habitatSides.put(i, Habitats.toHabitat(habitats[0]));
            }
        }else{
            habitatSides.put(5, Habitats.toHabitat(habitats[0]));
            habitatSides.put(0, Habitats.toHabitat(habitats[0]));
            habitatSides.put(1, Habitats.toHabitat(habitats[0]));
            habitatSides.put(2, Habitats.toHabitat(habitats[1]));
            habitatSides.put(3, Habitats.toHabitat(habitats[1]));
            habitatSides.put(4, Habitats.toHabitat(habitats[1]));
        }

        for(int i = 0; i<animals.length; i++){
            this.animals.add(CardAnimals.StringToAnimal(animals[i]));
        }

        try{
            //image = ImageIO.read(new File("src/Entities/StarterTilePics/"+imageName+".png"));
            image = ImageIO.read(Objects.requireNonNull(HabitatTiles.class.getResource("/Entities/StarterTilePics/" + imageName + ".png")));
            //System.out.println("image pulled: "+"/Entities/Images/"+imageName+".png");
        }catch(Exception _){
        }
        allTiles.put(this.imageName, this);
    }

    public static HabitatTiles getTile(String s){
        return allTiles.get(s);
    }



    public static void highlightGroup(HashSet<HabitatTiles> set, String s){
        for(HabitatTiles h:set){
            h.highlights.add(s);
        }
    }

    public static void highlightGroups(HashSet<HashSet<HabitatTiles>> set, String s){
        for(HashSet<HabitatTiles> group:set){
            highlightGroup(group, s);
        }
    }

    public static void highlightGroups(HashMap<HashSet<HabitatTiles>, Integer> map, String s){
        for(HashSet<HabitatTiles> group:map.keySet()){
            highlightGroup(group, s);
        }
    }

    public static void highlightGroups(HashSet<CardAnimals> set1, HashMap<CardAnimals, HashSet<HabitatTiles>> set2, String s){
        for(CardAnimals c:set1){
            highlightGroup(set2.get(c), s);
        }
    }

    public static void highlightGroups(HashMap<CardAnimals, Integer> map1, HashMap<CardAnimals, HashSet<HabitatTiles>> set2, String s){
        for(CardAnimals c:map1.keySet()){
            highlightGroup(set2.get(c), s);
        }
    }

    public WildlifeTokens getToken(){
        return super.getTokens();
    }

    public void setToken(WildlifeTokens w){
        super.addToken(w);
    }

    public void setToken(CardAnimals i){
        super.addToken(new WildlifeTokens(i));
    }

    public String tokenString(){
        return super.getTokens().toString();
    }

    public CardAnimals tokenAnimal(){
        if (super.getTokens()!=null) {
            return super.getTokens().getType();
        } else {
            return null;
        }
    }

    public Boolean habitatMatch(Integer connection){
        HabitatTiles c = this.get(connection);
        try {
            return this.habitatSides.get(connection).equals(c.getHabitats().get(c.getSideOf(this)));
        } catch (Exception e) {
            return false;
        }
    }

    public Integer getNumberOf(CardAnimals i){
        Integer count = 0;
        for(int c: connections.keySet()){
            if(connections.get(c).tokenAnimal()!=null&&connections.get(c).tokenAnimal()==i){
                count++;
            }
        }
        return count;
    }
    @Override
    public void drawHexagon(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        if(isEmpty()){
            g.setColor(new Color(255, 255, 143));
            g.fillPolygon(super.getPolygon());
            g.setColor(Color.BLACK);
        }else{
            super.drawHexagon(g);
            Double offset = super.getSize();
            int yOffset = (int)(Math.round(offset));
            Double yo = -1*Math.sqrt(3)/2.0*offset;
            int xOffset = (int)(Math.round(yo));

            int xPos = super.getXPos();
            int yPos = super.getYPos();
            
            BufferedImage newImage = rotate(image, Math.toRadians(rotation%360));

            int size = (int)Math.round(Math.sqrt(3)/2.0)*2*(int)(Math.round(super.getSize()));
            int sz = (int)Math.round(super.getSize()/2);
            if (super.getXPos()!=0&&super.getYPos()!=0){
                g.drawImage(newImage, xPos+xOffset,yPos-yOffset,(int)(super.getSize()*Math.sqrt(3)),(int)(super.getSize()*2),null);
                if (super.getTokens()!=null){
                    g.drawImage(super.getTokens().getImage(),xPos-sz,yPos-sz,sz*2,sz*2,null);
                }   
            }
        }
        
    }

    public void drawHighlight(Graphics g){
        Double offset = super.getSize();
            int yOffset = (int)(Math.round(offset));
            Double yo = -1*Math.sqrt(3)/2.0*offset;
            int xOffset = (int)(Math.round(yo));

            int xPos = super.getXPos();
            int yPos = super.getYPos();
        try {
            g.drawImage(ImageIO.read(Objects.requireNonNull(HabitatTiles.class.getResource("/Entities/Images/HexHighlight.png"))), xPos+xOffset,yPos-yOffset,(int)(super.getSize()*Math.sqrt(3)),(int)(super.getSize()*2),null);
            PrintTester.print("Highlight Drawn");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void drawScoringHighlight(Graphics g){
        Double offset = super.getSize();
            int yOffset = (int)(Math.round(offset));
            Double yo = -1*Math.sqrt(3)/2.0*offset;
            int xOffset = (int)(Math.round(yo));

            int xPos = super.getXPos();
            int yPos = super.getYPos();
        try {
            g.drawImage(ImageIO.read(Objects.requireNonNull(HabitatTiles.class.getResource("/Entities/Images/ScoringHighlight.png"))), xPos+xOffset,yPos-yOffset,(int)(super.getSize()*Math.sqrt(3)),(int)(super.getSize()*2),null);
            PrintTester.print("Highlight Drawn");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void drawSecondaryHighlight(Graphics g){
        Double offset = super.getSize();
            int yOffset = (int)(Math.round(offset));
            Double yo = -1*Math.sqrt(3)/2.0*offset;
            int xOffset = (int)(Math.round(yo));

            int xPos = super.getXPos();
            int yPos = super.getYPos();
        try {
            g.drawImage(ImageIO.read(Objects.requireNonNull(HabitatTiles.class.getResource("/Entities/Images/ScoringHighlight2.png"))), xPos+xOffset,yPos-yOffset,(int)(super.getSize()*Math.sqrt(3)),(int)(super.getSize()*2),null);
            PrintTester.print("Highlight Drawn");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void drawMouseHighlight(Graphics g){
        Double offset = super.getSize();
            int yOffset = (int)(Math.round(offset));
            Double yo = -1*Math.sqrt(3)/2.0*offset;
            int xOffset = (int)(Math.round(yo));

            int xPos = super.getXPos();
            int yPos = super.getYPos();
        try {
            g.drawImage(ImageIO.read(Objects.requireNonNull(HabitatTiles.class.getResource("/Entities/Images/MouseHighlight.png"))), xPos+xOffset,yPos-yOffset,(int)(super.getSize()*Math.sqrt(3)),(int)(super.getSize()*2),null);
            PrintTester.print("Highlight Drawn");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void drawHexagon(Graphics g, Double radius, int x, int y){
        Graphics2D g2d = (Graphics2D) g;
        if(isEmpty()){
        }else{
            Double offset = radius+0.0;
            int yOffset = (int)(Math.round(offset));
            Double yo = -1*Math.sqrt(3)/2.0*offset;
            int xOffset = (int)(Math.round(yo));

            int xPos = x;
            int yPos = y;
            
            BufferedImage newImage = rotate(image, Math.toRadians(rotation%360));

            int size = (int)Math.round(Math.sqrt(3)/2.0)*2*(int)(Math.round(radius));
            int sz = (int)Math.round(radius/2);
            if (x!=0&&y!=0){
                g.drawImage(newImage, xPos+xOffset,yPos-yOffset,(int)(radius*Math.sqrt(3)),(int)(radius*2),null);
                //g.drawImage(newImage, xPos+xOffset,yPos-yOffset,(int)(50*Math.sqrt(3)),(int)(50*2),null);
                if (super.getTokens()!=null){
                    g.drawImage(super.getTokens().getImage(),xPos-sz,yPos-sz,sz*2,sz*2,null);
                }   
            }
        }
        
    }

    public MathPoint getCoordinate(){
        return new MathPoint(getXPos(), getYPos());
    }

    public void setCoordinate(MathPoint m){
        super.setX(m.xPoint);
        super.setY(m.yPoint);

    }

    //rotational stuff
    public static BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(w, h, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    public void replaceNullConnectionsWithEmpty(){
        for(int i = 0; i<6; i++){
            if(connections.get(i)==null){
                add(new HabitatTiles(super.getSize()), i);
            }
        }
    }

    public HabitatTiles add(HabitatTiles h, Integer side){
        h.unsafeAdd(this, next(side, 3));
        h.setCoordinate(this.getAdjacentTileOffset(side));
        return connections.put(side, h);
    }

    HabitatTiles unsafeAdd(HabitatTiles h, Integer side){
        return connections.put(side, h);
    }

    public HabitatTiles get(Integer side){
        try {
            return connections.get(side);
        } catch (Exception e) {
            return null;
        }
    }

    public Integer getSideOf(HabitatTiles h){
        for(Integer i:connections.keySet()){
            if(connections.get(i).equals(h)){
                return i;
            }
        }
        return -1;
    }

    public void setOffset(int r){
        int offsetBy = r-rotation;
        for(int i = 0; i<offsetBy; i+=60){
            rotate();
        }
    }

    public void rotate(){
        rotation+=60;
        HashMap<Integer, Habitats> temp = new HashMap<Integer, Habitats>();
        for(int i = 0; i<6; i++){
            temp.put(previousInt(i), habitatSides.get(i));
        }
        habitatSides = temp;
        if (rotation>360){
            rotation-=360;
        }
        PrintTester.print(imageName+" rotated: "+habitatSides.toString());
    }
    public void rotateC(){
        rotation+=300;
        HashMap<Integer, Habitats> temp = new HashMap<Integer, Habitats>();
        for(int i = 0; i<6; i++){
            temp.put(nextInt(i), habitatSides.get(i));
        }
        habitatSides = temp;
        if (rotation>360){
            rotation-=360;
        }
        PrintTester.print(imageName+" rotated: "+habitatSides.toString());
    }

    public int getRotation(){
        return rotation;
    }

    public static Integer nextInt(Integer i){
        //gets number representing next side, going clockwise.
        return (i+1) % 6;
    }

    public static Integer previousInt(Integer i){
        //gets number representing previous side, going clockwise.
        for(int j = 0; j<5; j++){
            i = nextInt(i);
        }
        return i;
    }

    public static Integer next(Integer i, Integer increment){
        //gets number representing next [increment] side, going clockwise.
        Integer ret = i;
        for(int c = 0; c<increment; c++){
            ret = nextInt(ret);
        }
        return ret;
    }

    public static Integer previous(Integer i, Integer decrement){
        //gets number representing previous [increment] side, going clockwise.
        Integer ret = i;
        for(int c = 0; c<decrement; c++){
            ret = previousInt(ret);
        }
        return ret;
    }

    public Image getImage(){
        return image;
    }

    public HashMap<Integer, HabitatTiles> getConnections(){
        return connections;
    }

    public HabitatTiles replaceWith(HabitatTiles h){
        HabitatTiles removed = new HabitatTiles(super.getSize());
        h.setCoordinate(this.getCoordinate());
        for(HabitatTiles t:this.connections.values()){
            removed = t.unsafeAdd(h, t.getSideOf(this));
            h.unsafeAdd(t, this.getSideOf(t));
        }
        return removed;
    }

    public Boolean isEmpty(){
        return imageName.contains("empty");
    }

    public HabitatTiles findFirstWithSpecificToken(CardAnimals token){
        for(Integer i:connections.keySet()){
            if(connections.get(i).tokenAnimal()!=null&&connections.get(i).tokenAnimal().equals(token)){
                return connections.get(i);
            }
        }
        return null;
    }

    public HashMap<Integer, Habitats> getHabitats(){
        return habitatSides;
    }

    public boolean equals(Object o){
        HabitatTiles h = (HabitatTiles)o;
        return h.imageName.equals(this.imageName);
    }

    public boolean canPick(WildlifeTokens tk){
        if (super.getTokens()==null&&tk!=null){
            return animals.contains(tk.getType());
        }   

        return false;
    }

    //access for the imagename
    public String getImageName(){
        if(imageName!=null){
            return imageName;
        }
        return null;
    }

    public MathPoint getAdjacentTileOffset(Integer direction){
        Double x = super.getXPos()+0.0;
        Double y = super.getYPos()+0.0;
        Double size = super.getSize();

        if(Objects.equals(direction, LEFT)){
            x -= Math.sqrt(3)*size;
        }else if(Objects.equals(direction, RIGHT)){
            x += Math.sqrt(3)*size;
        }else if(Objects.equals(direction, UP_RIGHT)){
            x += size*Math.sqrt(3)/2.0;
            y -= size*3/2.0;
        }else if(Objects.equals(direction, UP_LEFT)){
            x += -1*size*Math.sqrt(3)/2.0;
            y -= size*3/2.0;
        }else if(Objects.equals(direction, DOWN_RIGHT)){
            x += size*Math.sqrt(3)/2.0;
            y -= -1*size*3/2.0;
        }else if(Objects.equals(direction, DOWN_LEFT)){
            x += -1*size*Math.sqrt(3)/2.0;
            y -= -1*size*3/2.0;
        }

        Integer xPoint = (int)(double)x;
        Integer yPoint = (int)(double)y;

        return new MathPoint(xPoint, yPoint); 
    }       

    public static MathPoint getAdjacentTileOffset(MathPoint p, Double size, Integer direction){
        Double x = p.xPoint+0.0;
        Double y = p.yPoint+0.0;
        

        if(Objects.equals(direction, LEFT)){
            x -= Math.sqrt(3)*size;
        }else if(Objects.equals(direction, RIGHT)){
            x += Math.sqrt(3)*size;
        }else if(Objects.equals(direction, UP_RIGHT)){
            x += size*Math.sqrt(3)/2.0;
            y -= size*3/2.0;
        }else if(Objects.equals(direction, UP_LEFT)){
            x += -1*size*Math.sqrt(3)/2.0;
            y -= size*3/2.0;
        }else if(Objects.equals(direction, DOWN_RIGHT)){
            x += size*Math.sqrt(3)/2.0;
            y -= -1*size*3/2.0;
        }else if(Objects.equals(direction, DOWN_LEFT)){
            x += -1*size*Math.sqrt(3)/2.0;
            y -= -1*size*3/2.0;
        }

        Integer xPoint = (int)(double)x;
        Integer yPoint = (int)(double)y;

        return new MathPoint(xPoint, yPoint); 
    }       

    // do they equal each other 
    public boolean equals(HabitatTiles t){
        if(t.getImageName().equals(imageName)){
            return true;
        }
        return false;
    }

    public String toString(){
        return imageName;
    }
}
