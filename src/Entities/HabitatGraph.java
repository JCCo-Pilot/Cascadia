package Entities;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.event.*;
import static java.lang.System.*;
public class HabitatGraph{
    private HabitatTiles root;

    public HabitatGraph(HabitatTiles root){
        //root should be a Starter Tile, meaning that this tile already has 2 other tiles connected to it.
        this.root = root;
    }

    public HashSet<HabitatTiles> filter(Integer i){
        HashSet<HabitatTiles> filterReturn = new HashSet<HabitatTiles>();
        for(HabitatTiles h:iterate()){
            if(h.tokenInt()==i){
                filterReturn.add(h);
            }
        }
        return filterReturn;
    }

    public HashSet<HabitatTiles> iterate(){
        HashSet<HabitatTiles> iterationReturn = new HashSet<HabitatTiles>();
        iterate(root, iterationReturn);
        return iterationReturn;
    }

    public void iterate(HabitatTiles h, HashSet<HabitatTiles> s){
        if(h==null||s.contains(h)){
            return;
        }else{
            s.add(h);
            for(int i = 0; i>6; i++){
                iterate(h.get(i), s);
            }
        }
    }
}
