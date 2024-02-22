package Entities;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.event.*;
import static java.lang.System.*;
public class HabitatTiles {
    public static Integer LEFT = 0;
    public static Integer DOWN_LEFT = 1;
    public static Integer DOWN_RIGHT = 2;
    public static Integer RIGHT = 3;
    public static Integer UP_RIGHT = 4;
    public static Integer UP_LEFT = 5;

    private HashSet<String> animals = new HashSet<String>();
    private HashMap<Integer, String> habitatSides = new HashMap<Integer, String>();
    private HashMap<Integer, HabitatTiles> connections = new HashMap<Integer, HabitatTiles>();
    private Integer rotation = 0;
    private String imageName;
    public Boolean isKeystone;
    public Integer x, y;

    public HabitatTiles(String imageName, String[] habitats, String[] animals, Boolean isKeyStone){
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

    public HabitatTiles add(HabitatTiles h, Integer side){
        return connections.put(side, h);
    }

    public HabitatTiles get(Integer side){
        return connections.get(side);
    }

    public void rotate(){
        rotation++;
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
}
