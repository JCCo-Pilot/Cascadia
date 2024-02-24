package Entities;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.event.*;
import static java.lang.System.*;
public class WildlifeTokens {
    public final static Integer BEAR = 0;
    public final static Integer ELK = 1;
    public final static Integer SALMON = 2;
    public final static Integer HAWK = 3;
    public final static Integer FOX = 4;
    private String[]type = {"Bear","Elk","Salmon","Hawk", "Fox"};
    private BufferedImage image;
    private int var;
    public WildlifeTokens(int i){
        var = i;
        try{
            switch(var){
                case 0:
                    image = ImageIO.read(new File("C:/Cascadia/src//Entities/Tokens/BearToken.png"));
                    //image = ImageIO.read(WildlifeTokens.class.getResource("/Tokens/BearToken.png"));
                break;  
                case 1:
                    image = ImageIO.read(new File("C:/Cascadia/src//Entities/Tokens/ElkToken.png"));
                    //image = ImageIO.read(WildlifeTokens.class.getResource("/Tokens/ElkToken.png"));
                break;
                case 2:
                    image = ImageIO.read(new File("C:/Cascadia/src//Entities/Tokens/SalmonToken.png"));
                    //image = ImageIO.read(WildlifeTokens.class.getResource("/Tokens/SalmonToken.png"));
                break;
                case 3:
                    image = ImageIO.read(new File("C:/Cascadia/src//Entities/Tokens/HawkToken.png"));
                    //image = ImageIO.read(WildlifeTokens.class.getResource("/Tokens/HawkToken.png"));
                break;
                case 4:
                    image = ImageIO.read(new File("C:/Cascadia/src//Entities/Tokens/FoxToken.png"));
                    //image = ImageIO.read(WildlifeTokens.class.getResource("/Tokens/FoxToken.png"));
                break;
            }
        }catch(Exception e){
            out.println("Error in WildLife Tokens");
        }
    }
    @Override
    public String toString(){
        return type[var];
    }
    public String getName(){
        return type[var];
    }
    public int getType(){
        return var;
    }
    public BufferedImage getImage(){
        return image;
    }
}
