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
    private String[]type = {"Bear","Elk","Salmon","Fox"};
    private BufferedImage image;
    private int var;
    public WildlifeTokens(int i){
        var = i;
        try{
            switch(var){
                case 0:
                    image = ImageIO.read(WildlifeTokens.class.getResource("/Image/Bear.png"));
                break;  
                case 1:
                break;
                case 2:
                break;
                case 3:
                break;
            }
        }catch(Exception e){
            out.println("Error in WildLife Tokens");
        }
    }
}
