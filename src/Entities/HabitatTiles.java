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
    //private HashSet<CardAnimals> animals = new HashSet<CardAnimals>();
    private ArrayList<CardAnimals> animals = new ArrayList<CardAnimals>();
    
    private HashMap<Integer, Habitats> habitatSides = new HashMap<Integer, Habitats>();
    private HashMap<Integer, HabitatTiles> connections = new HashMap<Integer, HabitatTiles>();
    private Integer rotation = 0;
    private String imageName;
    public Boolean isKeystone;
    public Integer x, y;
    private static int emptyCnt;

    //CONSTRUCTORS*******************************************************************************************************
    public HabitatTiles(double size){
        super(0, 0, (int)size);
        imageName = "empty"+ ++emptyCnt;
    }

    public HabitatTiles(){
        super(0, 0, 0);
        imageName = "empty empty";
    }

    public HabitatTiles(String imageName,int x, int y, Double size){
        super(x,y,size);
        //imageName = imageName.substring(5);
        try{
            image = ImageIO.read(new File("src/Entities/Images/"+imageName+".png"));
        }catch(Exception e){
            out.println("Shit fucked up");
        }
    }   

    public HabitatTiles(Integer x, Integer y, Double size){
        super(x, y, size);
        imageName = "N/A";
    }

    public HabitatTiles(String imageName,boolean isKey,int x, int y, double sz){
        super(x,y,sz);
        this.imageName = imageName;
        this.isKeystone = isKey;

        String env = imageName.substring(0, imageName.indexOf("-"));
        String ani = imageName.substring(imageName.indexOf("-")+1);

        ArrayList<String>environments = new ArrayList<>();
        ArrayList<String>animals = new ArrayList<>();
        
        //sample input:swamp-hawk-key
        
        //environmental processing
        if (env.contains("+")){
            environments.add(env.substring(0,env.indexOf("+")));
            env = env.substring(env.indexOf("+")+1);
        }
        environments.add(env);

        //habitat processing
        if(ani.indexOf("-")!=ani.lastIndexOf("-")){
            //3 habitats
        }
    }

    public HabitatTiles(String imageName, String[] habitats, String[] animals, Boolean isKeyStone){
        super(0, 0, 0);
        this.imageName = imageName;
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
            image = ImageIO.read(new File("src/Entities/Images/"+imageName+".png"));
        }catch(Exception e){
            out.println("Shit fucked up");
        }
    }

    public HabitatTiles(String imageName, String[] habitats, String[] animals, boolean isKeyStone, Integer x, Integer y, Double size){
        super(x, y, size);
        this.imageName = imageName;
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
            image = ImageIO.read(new File("src/Entities/Images/"+imageName+".png"));
        }catch(Exception e){
            out.println("Shit fucked up");
        }
    }

    public HabitatTiles(String imageName, String[] habitats, String[] animals, boolean isKeyStone, Integer x, Integer y, Double size,boolean isStarter){
        super(x, y, size);
        this.imageName = imageName;
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
            image = ImageIO.read(new File("src/Entities/StarterTilePics/"+imageName+".png"));
        }catch(Exception e){
            out.println("Shit fucked up");
        }
    }
    
    //WILDLIFE TOKEN METHODS*******************************************************************************************************
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
        //System.out.println("HabitatSides this "+habitatSides.toString());
        //System.out.println("HabitatSides c "+c.getHabitats().toString());
        try {
            return this.habitatSides.get(connection).equals(c.getHabitats().get(c.getSideOf(this)));
        } catch (Exception e) {
            return false;
        }
    }

    public Integer getNumberOf(CardAnimals i){
        Integer count = 0;
        for(int c: connections.keySet()){
            if(connections.get(c).tokenAnimal()==i){
                count++;
            }
        }
        return count;
    }
    @Override
    public void drawHexagon(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        if(isEmpty()){
            //super.drawHexagon(g);
            //g.drawString(imageName, super.getXPos()+(int)(Math.random()*5), super.getYPos()+(int)(Math.random()*5));
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

            int size = (int)Math.round(Math.sqrt(3)/2.0)*2*(int)(Math.round(70.0));
            int sz = (int)Math.round(super.getSize()/2);
            if (super.getXPos()!=0&&super.getYPos()!=0){
                g.drawImage(newImage, xPos+xOffset,yPos-yOffset,(int)(super.getSize()*Math.sqrt(3)),(int)(super.getSize()*2),null);
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
        //System.out.println("Coords of "+this+" set to "+m.xPoint+", "+m.yPoint);
    }
    //rotational stuff
    public static BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(w, h, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        //g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }
    //it works
    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
    //MISC*******************************************************************************************************
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
        System.out.println(imageName+" rotated: "+habitatSides.toString());
        //out.println("Rotation- "+getRotation());
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
        System.out.println(imageName+" rotated: "+habitatSides.toString());
        //out.println("Rotation- "+getRotation());
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
            if(connections.get(i).tokenAnimal().equals(token)){
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
        //out.println("canPick method:"+tk.toString());
        if (super.getTokens()==null&&super.getTokens()==null&&tk!=null){
            /*if(animals.contains(CardAnimals.StringToAnimal(tk.getName()))){
                System.out.println("Called");
                return true;
            }*/
            //out.println("Name"+tk.getName());
            if(animals.contains(tk.getType())){
                //out.println("canPick = true");
                return true;
            }
        }   
        //out.println("canPick = false");
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

        if(direction==LEFT){
            x -= Math.sqrt(3)*size;
        }else if(direction==RIGHT){
            x += Math.sqrt(3)*size;
        }else if(direction==UP_RIGHT){
            x += size*Math.sqrt(3)/2.0;
            y -= size*3/2.0;
        }else if(direction==UP_LEFT){
            x += -1*size*Math.sqrt(3)/2.0;
            y -= size*3/2.0;
        }else if(direction==DOWN_RIGHT){
            x += size*Math.sqrt(3)/2.0;
            y -= -1*size*3/2.0;
        }else if(direction==DOWN_LEFT){
            x += -1*size*Math.sqrt(3)/2.0;
            y -= -1*size*3/2.0;
        }

        //System.out.println(direction + " offset of "+this+" is "+x+", "+y);

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
