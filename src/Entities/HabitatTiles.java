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
    public WildlifeTokens token = null;

    //CONSTRUCTORS*******************************************************************************************************
    public HabitatTiles(){
        super(0, 0, 0);
        imageName = "empty";
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

    public Boolean isEmpty(){
        return imageName=="empty";
    }
    
    //WILDLIFE TOKEN METHODS*******************************************************************************************************
    public WildlifeTokens getToken(){
        return token;
    }

    public void setToken(WildlifeTokens w){
        token = w;
    }

    public void setToken(CardAnimals i){
        token = new WildlifeTokens(i);
    }

    public String tokenString(){
        return token.toString();
    }

    public CardAnimals tokenAnimal(){
        return token.getType();
    }

    public Boolean habitatMatch(Integer connection){
        HabitatTiles c = this.get(connection);
        return this.habitatSides.get(connection).equals(c.habitatSides.get(c.getSideOf(this)));
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
        try {
            Double offset = super.getSize();
            int yOffset = (int)(Math.round(offset));
            Double yo = -1*Math.sqrt(3)/2.0*offset;
            int xOffset = (int)(Math.round(yo));
    
            int xPos = super.getXPos();
            int yPos = super.getYPos();
            
            BufferedImage newImage = rotate(image, Math.toRadians(rotation%360));
    
            int size = (int)Math.round(Math.sqrt(3)/2.0)*2*(int)(Math.round(70.0));
            if (super.getXPos()!=0&&super.getYPos()!=0){
                g.drawImage(newImage, xPos+xOffset,yPos-yOffset,121,140,null);
                if (super.getTokens()!=null){
                    g.drawImage(super.getTokens().getImage(),xPos-35,yPos-35,70,70,null);
                }   
            }
        } catch (Exception e) {
            super.drawHexagon(g);
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
            if(!connections.containsKey(i)){
                connections.put(i, new HabitatTiles());
            }
        }
    }

    public HabitatTiles add(HabitatTiles h, Integer side){
        h.unsafeAdd(this, next(side, 3));
        h.setCoordinate(this.getAdjacentTileOffset(side));
        return connections.put(side, h);
    }

    private HabitatTiles unsafeAdd(HabitatTiles h, Integer side){
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
        rotation = r;
    }

    public void rotate(){
        rotation+=60;
        HashMap<Integer, Habitats> temp = new HashMap<Integer, Habitats>();
        for(int i = 0; i<6; i++){
            temp.put(previousInt(i), habitatSides.get(i));
        }
        habitatSides = temp;
        
        out.println("Rotation- "+getRotation());
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
        return (i-1) % 6;
    }

    public static Integer next(Integer i, Integer increment){
        //gets number representing next [increment] side, going clockwise.
        Integer ret = i;
        for(int c = 0; c<increment; c++){
            ret = (ret+1) % 6;
        }
        return ret;
    }

    public static Integer previous(Integer i, Integer decrement){
        //gets number representing previous [increment] side, going clockwise.
        Integer ret = i;
        for(int c = 0; c<decrement; c++){
            ret = (ret-1) % 6;
        }
        return ret;
    }

    public Image getImage(){
        //TODO: idk how the images will be stored so
        try {
            return ImageIO.read(new File(imageName+".png"));
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

    public HashMap<Integer, HabitatTiles> getConnections(){
        return connections;
    }

    public HabitatTiles replaceWith(HabitatTiles h){
        HabitatTiles removed = new HabitatTiles();
        for(HabitatTiles t:this.connections.values()){
            removed = t.add(h, t.getSideOf(this));
        }
        return removed;
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
        if (token==null&&tk!=null){
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

    public String toString(){
        return imageName;
    }

    public MathPoint getAdjacentTileOffset(Integer direction){
        Double x = 0.0;
        Double y = 0.0;
        Double size = super.getSize();

        if(direction==LEFT){
            x = size*-1;
        }else if(direction==RIGHT){
            x = size;
        }else if(direction==UP_RIGHT){
            x = size*Math.sqrt(3)/2.0;
            y = size*3/2.0;
        }else if(direction==UP_LEFT){
            x = -1*size*Math.sqrt(3)/2.0;
            y = size*3/2.0;
        }else if(direction==DOWN_RIGHT){
            x = size*Math.sqrt(3)/2.0;
            y = -1*size*3/2.0;
        }else if(direction==DOWN_LEFT){
            x = -1*size*Math.sqrt(3)/2.0;
            y = -1*size*3/2.0;
        }

        Integer xPoint = (int)(double)x;
        Integer yPoint = (int)(double)y;

        return new MathPoint(xPoint, yPoint);        
    }
}
