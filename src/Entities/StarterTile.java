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

    private final Double r3 = 1.73205080757;
    private final Double r3o2 = 0.86602540378;

    private int xPos,yPos;
    private Double size;
    
    public StarterTile(HabitatTiles tile_down_left, HabitatTiles tile_up, HabitatTiles tile_down_right){
        down_left = tile_down_left;
        down_right = tile_down_right;
        up = tile_up;

        xPos = 0; yPos = 0;
    }

    public void setPos(int x, int y, Double sz){
        xPos = x;
        yPos = y;
        size =sz;
    }

    public void calculatePos(){
        int half = (int)(Math.round(size/2));
        int offset = (int)(Math.round(r3o2*size));
        up.setPos(xPos, yPos-(int)(Math.round(size)), size);
        down_left.setPos(xPos-offset,yPos+half,size);
        down_right.setPos(xPos+offset,yPos+half,size);
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
