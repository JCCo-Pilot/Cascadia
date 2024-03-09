package Entities;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.*;
public class StarterTile {
    public HabitatTiles down_left = null;
    public HabitatTiles down_right = null;
    public HabitatTiles up = null;

    private int xPos,yPos;
    
    public StarterTile(HabitatTiles tile_down_left, HabitatTiles tile_up, HabitatTiles tile_down_right){
        down_left = tile_down_left;
        down_right = tile_down_right;
        up = tile_up;

        xPos = 0; yPos = 0;
    }

    public void setPos(int x, int y){
        xPos = x;
        yPos = y;
    }

    public void calculatePos(){
        
    }

    public void paintStarter(Graphics g){
        if (xPos !=0&&yPos!=0){
            if (down_left!=null){

            }
            if (down_right!=null){

            }
            if (up!=null){

            }
        }
    }
}
