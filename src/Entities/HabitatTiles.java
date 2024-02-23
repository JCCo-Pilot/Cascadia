package Entities;
import java.awt.*;
import javax.swing.*;
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
    private HashSet<String> animals = new HashSet<String>();
    private HashMap<Integer, String> habitatSides = new HashMap<Integer, String>();
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

    public HabitatTiles(Integer x, Integer y, Double size){
        super(x, y, size);
        imageName = "N/A";
    }


    public HabitatTiles(String imageName, String[] habitats, String[] animals, Boolean isKeyStone){
        super(0, 0, 0);
        this.imageName = imageName;
        this.isKeystone = isKeyStone;

        //map each side to a habitat
        if(isKeystone){
            for(int i = 0; i<6; i++){
                habitatSides.put(i, habitats[0]);
            }
        }else{
            habitatSides.put(5, habitats[0]);
            habitatSides.put(0, habitats[0]);
            habitatSides.put(1, habitats[0]);
            habitatSides.put(2, habitats[1]);
            habitatSides.put(3, habitats[1]);
            habitatSides.put(4, habitats[1]);
        }

        for(int i = 0; i<animals.length; i++){
            this.animals.add(animals[i]);
        }
    }

    public HabitatTiles(String imageName, String[] habitats, String[] animals, Boolean isKeyStone, Integer x, Integer y, Double size){
        super(x, y, size);
        this.imageName = imageName;
        this.isKeystone = isKeyStone;

        //map each side to a habitat
        if(isKeystone){
            for(int i = 0; i<6; i++){
                habitatSides.put(i, habitats[0]);
            }
        }else{
            habitatSides.put(5, habitats[0]);
            habitatSides.put(0, habitats[0]);
            habitatSides.put(1, habitats[0]);
            habitatSides.put(2, habitats[1]);
            habitatSides.put(3, habitats[1]);
            habitatSides.put(4, habitats[1]);
        }

        for(int i = 0; i<animals.length; i++){
            this.animals.add(animals[i]);
        }
    }
    
    //WILDLIFE TOKEN METHODS*******************************************************************************************************
    public WildlifeTokens getToken(){
        return token;
    }

    public void setToken(WildlifeTokens w){
        token = w;
    }

    public void setToken(Integer i){
        token = new WildlifeTokens(i);
    }

    public String tokenString(){
        return token.toString();
    }

    public Integer tokenInt(){
        return token.getType();
    }

    public Boolean habitatMatch(Integer connection){
        HabitatTiles c = this.get(connection);
        return this.habitatSides.get(connection).equals(c.habitatSides.get(c.getSideOf(this)));
    }

    public Integer getNumberOf(Integer i){
        Integer count = 0;
        for(int c: connections.keySet()){
            if(connections.get(c).tokenInt()==i){
                count++;
            }
        }
        return count;
    }
    @Override
    public void drawHexagon(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Double offset = super.getSize();
        int yOffset = (int)(Math.round(offset));
        Double yo = -1*Math.sqrt(3)/2.0*offset;
        int xOffset = (int)(Math.round(yo));

        /*AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(rotation), super.getX(), super.getY());
        g2d.setTranform(transform);*/
        g.drawImage(image, x+xOffset,y-yOffset,null);
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

    public void rotate(){
        rotation+=60;
        HashMap<Integer, String> temp = new HashMap<Integer, String>();
        for(int i = 0; i<6; i++){
            temp.put(previousInt(i), habitatSides.get(i));
        }
        habitatSides = temp;
    }

    public static Integer nextInt(Integer i){
        //gets number representing next side, going clockwise.
        return (i+1) % 6;
    }

    public static Integer previousInt(Integer i){
        //gets number representing previous side, going clockwise.
        return (i-1) % 6;
    }

    public Image getImage(){
        //TODO: idk how the images will be stored so
        try {
            return ImageIO.read(new File(imageName+".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<Integer, HabitatTiles> getConnections(){
        return connections;
    }

    public boolean equals(Object o){
        HabitatTiles h = (HabitatTiles)o;
        return h.imageName.equals(this.imageName);
    }

    
}
