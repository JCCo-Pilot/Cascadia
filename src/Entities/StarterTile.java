package Entities;
import java.util.*;
import java.awt.*;

public class StarterTile {
    public HabitatTiles down_left = null;
    public HabitatTiles down_right = null;
    public HabitatTiles up = null;

    private final Double rad3 = 1.73205080757;
    private final Double rad3div2 = 0.86602540378;

    private int xPos,yPos;
    private Double size;
    
    public StarterTile(ArrayList<HabitatTiles>tiles){
        for (int i =0;i<tiles.size()&&i<3;i++){
            switch (i) {
                case 0:
                    up = tiles.get(0);
                break;
                case 1:
                    down_left = tiles.get(1);
                break;
                case 2:
                    down_right = tiles.get(2);
                break;
            }
        }
    }

    public StarterTile(HabitatTiles tile_down_left, HabitatTiles tile_up, HabitatTiles tile_down_right){
        down_left = tile_down_left;
        down_right = tile_down_right;
        up = tile_up;
        xPos = 0; yPos = 0;
    }

    public StarterTile setPos(int x, int y, Double sz){
        xPos = x;
        yPos = y;
        size =sz;
        calculatePos();
        return this;
    }

    public void calculatePos(){
        up.setPos(437, 385, size);
        down_left.setPos(376,490,size);
        down_right.setPos(498,490,size);
    }

    public void paintStarter(Graphics g){
        if (xPos !=0&&yPos!=0){
            if (down_left!=null){
                down_left.drawHexagon(g);
            }
            if (down_right!=null){
                down_right.drawHexagon(g);
            }
            if (up!=null){
                up.drawHexagon(g);
            }
        }
    }

    public ArrayList<HabitatTiles>getTiles(){
        ArrayList<HabitatTiles>hts = new ArrayList<>();
        hts.add(up);
        hts.add(down_left);
        hts.add(down_right);
        return hts;
    }
}
