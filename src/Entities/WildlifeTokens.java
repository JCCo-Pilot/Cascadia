package Entities;
import java.awt.*;
import javax.swing.*;

import Entities.Enums.CardAnimals;

import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.event.*;
import static java.lang.System.*;
public class WildlifeTokens {
    private CardAnimals animalType;
    private int xPos,yPos;
    //private buffered image
    public WildlifeTokens(CardAnimals animalType){
        this.animalType = animalType;
        xPos = 0;
        yPos = 0;
    }
    @Override
    public String toString(){
        return animalType.toString();
    }
    public String getName(){
        return this.toString();
    }
    public CardAnimals getType(){
        return animalType;
    }
    public BufferedImage getImage(){
        BufferedImage image = null;
        try{
            switch(animalType){
                case BEAR:
                    //image = ImageIO.read(new File("src/Entities/Tokens/BearToken.png"));
                    image = ImageIO.read(WildlifeTokens.class.getResource("/Entities/Tokens/BearToken.png"));
                break;  
                case ELK:
                    //image = ImageIO.read(new File("src/Entities/Tokens/ElkToken.png"));
                    image = ImageIO.read(WildlifeTokens.class.getResource("/Entities/Tokens/ElkToken.png"));
                break;
                case SALMON:
                    //image = ImageIO.read(new File("src/Entities/Tokens/SalmonToken.png"));
                    image = ImageIO.read(WildlifeTokens.class.getResource("/Entities/Tokens/SalmonToken.png"));
                break;
                case HAWK:
                    //image = ImageIO.read(new File("src/Entities/Tokens/HawkToken.png"));
                    image = ImageIO.read(WildlifeTokens.class.getResource("/Entities/Tokens/HawkToken.png"));
                break;
                case FOX:
                    //image = ImageIO.read(new File("src/Entities/Tokens/FoxToken.png"));
                    image = ImageIO.read(WildlifeTokens.class.getResource("/Entities/Tokens/FoxToken.png"));
                break;
            }
        }catch(Exception e){
            out.println("Error in WildLife Tokens Image");
        }
        return image;
    }
}
